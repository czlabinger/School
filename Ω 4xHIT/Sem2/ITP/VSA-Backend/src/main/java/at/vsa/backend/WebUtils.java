package at.vsa.backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class WebUtils {

    public static ResponseEntity<?> createRedirectResponse(final String redirectURL) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", redirectURL);

        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    public static ResponseEntity<?> createRedirectResponse(final String redirectURL, Map<String, String> queryParameters) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(redirectURL);
        for(String key : queryParameters.keySet()) {
            builder.replaceQueryParam(key, queryParameters.get(key));
        }
        String fr = builder.toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(fr));

        return new ResponseEntity<String>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
