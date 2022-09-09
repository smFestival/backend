package sunrise.smfestival.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponseEntity> handleException(CustomException e){
        log.warn("error : {}",e.getErrorCode());
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> httpMessageNotReadableException(HttpMessageNotReadableException e){
        HashMap<String,String> error = new HashMap<>();
        error.put("error","body 정보가 없습니다");
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<HashMap<String,String>> RuntimeException(RuntimeException e){
        HashMap<String,String> error = new HashMap<>();
        error.put("error","서버의 오류로 인하여 처리되지 못 하였습니다 관리자에게 문의해주세요");
        e.printStackTrace();
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
