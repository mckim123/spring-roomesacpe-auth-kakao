package nextstep.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Object> handleException(CustomException e) {
        return ResponseEntity
                .status(HttpStatus.valueOf(e.getErrorCode().getStatus()))
                .body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(value = JwtException.class)
    protected ResponseEntity<Object> handleJwtException() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(ErrorCode.JWT_EXCEPTION));
    }
}
