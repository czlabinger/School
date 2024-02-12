package at.vsa.backend.assets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/assets")
public class AssetsController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAsset(@RequestParam("asset") MultipartFile file) {
        return ResponseEntity.ok("");
    }

}