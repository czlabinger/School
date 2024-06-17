package at.vsa.backend.user.entities;

import at.vsa.backend.FileHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.security.Permission;
import java.util.Collections;
import java.util.Set;

//TODO: rename methods, no idea what half of these do @Christof Zlabinger
//TODO: remove unused variables
public enum Role {
    BASIC(Collections.emptySet(), 3000000, 0),
    ADVANCED(Collections.emptySet(), 7000000, 0),
    PREMIUM(Collections.emptySet(), 15000000, 0),
    ADMIN(Collections.emptySet(), 99999999, 99999);

    private final Set<Permission> permissions;

    private final long filesizeLimit; // In bytes
    
    private final int fileLimit;

    Role(Set<Permission> permissions, long filesizeLimit, int fileLimit) {
        this.permissions = permissions;
        this.filesizeLimit = filesizeLimit;
        this.fileLimit = fileLimit;
    }

    public int getFileLimit(User u) {

        int version = u.getSubversion();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/currentVersions.json"));

            Long basicVersion = (Long) jsonObject.get("BASIC");
            Long advancedVersion = (Long) jsonObject.get("ADVANCED");
            Long premiumVersion = (Long) jsonObject.get("PREMIUM");

            JSONObject jsonObject2;
            switch (u.getRole().name()) {
                case "BASIC":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/BASIC/v" + version + ".json"));
                    return (int) (long) jsonObject2.get("fileLimit");
                case "ADVANCED":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/ADVANCED/v" + version + ".json"));
                    return (int) (long) jsonObject2.get("fileLimit");
                case "PREMIUM":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/PREMIUM/v" + version + ".json"));
                    return (int) (long) jsonObject2.get("fileLimit");
                case "ADMIN":
                    return 99999999;
                default:
                    throw new RuntimeException();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public int getFilesizeLimit(User u) {
        int version = u.getSubversion();
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/currentVersions.json"));

            Long basicVersion = (Long) jsonObject.get("BASIC");
            Long advancedVersion = (Long) jsonObject.get("ADVANCED");
            Long premiumVersion = (Long) jsonObject.get("PREMIUM");

            JSONObject jsonObject2;
            switch (u.getRole().name()) {
                case "BASIC":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/BASIC/v" + version + ".json"));
                    return (int) (long) jsonObject2.get("filesizeLimit");
                case "ADVANCED":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/ADVANCED/v" + version + ".json"));
                    System.out.println(jsonObject2.get("filesizeLimit"));
                    return (int) (long) jsonObject2.get("filesizeLimit");
                case "PREMIUM":
                    jsonObject2 = (JSONObject) parser.parse(FileHelper.getResourceFileAsString("subscription_versioning/PREMIUM/v" + version + ".json"));
                    return (int) (long) jsonObject2.get("filesizeLimit");
                case "ADMIN":
                    return 99999999;
                default:
                    throw new RuntimeException();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
