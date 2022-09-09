package sunrise.smfestival.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"해당하는 게시글을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCHED(HttpStatus.NOT_ACCEPTABLE,"비밀번호가 틀립니다."),
    ALREADY_LIKED(HttpStatus.NOT_ACCEPTABLE,"이미 추천 되어 있습니다"),
    INVALID_REQUEST(HttpStatus.NOT_ACCEPTABLE,"추천을 하지 않은 상태입니다");

    private final HttpStatus httpStatus;
    private final String message;
}
