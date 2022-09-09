package sunrise.smfestival.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CommonCode {

    OK(HttpStatus.OK,"정상 처리 되었습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
