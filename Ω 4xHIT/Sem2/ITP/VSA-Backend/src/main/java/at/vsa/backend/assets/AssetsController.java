package at.vsa.backend.assets;

import at.vsa.backend.JsonHelper;
import at.vsa.backend.user.UserService;
import at.vsa.backend.user.entities.LinkToken;
import at.vsa.backend.user.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static at.vsa.backend.BackendApplication.BUCKET_NAME;

@RestController
@RequestMapping("/assets")
@CrossOrigin(origins = "*") //TODO: rethink a solution for CrossOrigin
@RequiredArgsConstructor
public class AssetsController {

    private final UserService userService;
    private final LinkTokenHelper linkTokenHelper;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(HttpServletRequest request, final MultipartFile file, final String filename, final String contact) {


        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(403).body("Not logged in");

        if(filename.length() > 40)
            return ResponseEntity.badRequest().body("Filename too long (> 40 characters)");

        if(filename.contains("/") || filename.contains("\\"))
            return  ResponseEntity.badRequest().body("Filename is not allowed to contain '/' or '\\'");


        if(!userService.isValidFilesize(user, file.getSize())) {
            System.out.println(file.getSize()); //TODO: better logs
            return ResponseEntity.badRequest().body("This file is too large for your current plan");
        }

        if(!userService.checkFileLimit(user)) {
            return ResponseEntity.badRequest().body("You can't upload more files than " + user.getRole().getFileLimit(user));
        }

        File f = null;

        try {
            File d = new File("assets/");
            d.mkdirs();

            Path p = Paths.get("assets/" + filename);
            f = new File(p.toAbsolutePath().toString());
            f.createNewFile();
            file.transferTo(f);

            try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()) {

                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key(user.getEmail() + "/" + contact + "/" + f.getName())
                        .build();

                s3.putObject(putObjectRequest, RequestBody.fromFile(f));
            }

        } catch (IOException e) {
            System.err.println("IOException while trying to Upload to S3 Bucket\n" + e);
            return ResponseEntity.internalServerError().body("Internal Server Error");
        } finally {
            if(f != null)  f.delete();
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/list")
    public ResponseEntity<?> listFiles(HttpServletRequest request)  {

        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()){
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(user.getEmail())
                    .build();

            ListObjectsResponse listing = s3.listObjects(listObjectsRequest);
            List<S3Object> objects = listing.contents();

            List<Asset> assets = new ArrayList<>();

            for (S3Object object : objects) {
                assets.add(new Asset(object.key().substring(object.key().lastIndexOf('/') + 1),
                        object.key().substring(object.key().indexOf('/') + 1, object.key().lastIndexOf('/')),
                        object.size()));
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(assets);
        }
    }

    @PostMapping("/download")
    public ResponseEntity<?> downloadFile(HttpServletRequest request, String filename, String contact) {
                User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Path filePath = null;

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(user.getEmail() + "/" + contact + "/" + filename)
                    .build();

            filePath = Paths.get("assets/" + filename);
            File f = new File(filePath.toAbsolutePath().toString());

            // Delete the file if it already exists
            if (f.exists()) {
                f.delete();
            }

            s3.getObject(getObjectRequest, ResponseTransformer.toFile(filePath));

            if (f.exists()) {
                InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));
                return ResponseEntity.ok()
                        .contentLength(Files.size(filePath))
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + f.getName() + "\"")
                        .body(resource);
            } else  {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IOException e) {
            System.err.println("IOException while trying to Upload to S3 Bucket\n" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            if(filePath != null) {
                File f = new File(filePath.toAbsolutePath().toString());
                f.delete();
            }
        }
    }

    @PostMapping("/deletefile")
    public ResponseEntity<?> deleteFile(HttpServletRequest request, String filename, String contact) {

        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if(filename == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        if(contact == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


        ObjectIdentifier oi = ObjectIdentifier.builder().key(user.getEmail() + "/" + contact + "/" + filename).build();

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()) {
            DeleteObjectsRequest dor = DeleteObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .delete(Delete.builder().objects(oi).build())
                    .build();

            DeleteObjectsResponse delResp = s3.deleteObjects(dor);

            if (!delResp.errors().isEmpty()) {
                String err = String.format("errors returned while deleting %d objects",
                        delResp.errors().size());
                System.err.println(err);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (delResp.deleted().size() == 1) {
                String deleted = String.format(delResp.deleted().size() + " objects deleted");
                System.out.println(deleted);
                return ResponseEntity.ok().build();
           }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/getMaxSize")
    public ResponseEntity<?> getMaxSize(HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("maxSize", user.getRole().getFilesizeLimit(user));

        return ResponseEntity.ok().body(JsonHelper.toJSON(map));
    }

    @PostMapping("/getMaxFiles")
    public ResponseEntity<?> getMaxFiles(HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        if(user == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("maxFiles", user.getRole().getFileLimit(user));

        return ResponseEntity.ok().body(JsonHelper.toJSON(map));
    }

    @GetMapping("/contactList")
    public ResponseEntity<?> contactList(@RequestParam String token) {
        HashMap<String, String> uploadedFilesMap = new HashMap<>();

        LinkToken lt = linkTokenHelper.getLinkToken(token);

        if(lt == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        try (S3Client s3 = S3Client.builder().region(Region.EU_CENTRAL_1).build()) {
            ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                    .bucket(BUCKET_NAME)
                    .prefix(lt.getOriginalemail() + "/" + lt.getContact())
                    .build();

            ListObjectsResponse listing = s3.listObjects(listObjectsRequest);
            List<S3Object> objects = listing.contents();

            for (S3Object object : objects) {
                uploadedFilesMap.put(object.key(), createLink(object.key()));
            }
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(uploadedFilesMap);
    }

    public static String createLink(String object) {
        try (S3Presigner presigner = S3Presigner.create()) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(object)
                    .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofDays(7))
                    .getObjectRequest(getObjectRequest)
                    .build();

            PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
            return presignedRequest.url().toString();
        }
    }
}

