package cz.tslavik.kudos.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorDTO> handleException(ErrorException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(e.getErrorType().name());
        errorDTO.setDetail(e.getDetail());
        return new ResponseEntity<>(errorDTO,e.getErrorType().httpStatus);
    }
}
