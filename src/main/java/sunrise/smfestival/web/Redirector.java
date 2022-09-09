package sunrise.smfestival.web;

import io.swagger.v3.oas.models.headers.Header;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;

import java.net.URI;

@ApiIgnore
@RestController
public class Redirector {

    @GetMapping("/")
    public ResponseEntity<ResponseHeaders> redirect(){
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/swagger-ui/index.html"));
        return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
    }
}
