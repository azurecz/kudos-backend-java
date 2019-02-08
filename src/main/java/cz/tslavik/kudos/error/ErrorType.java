package cz.tslavik.kudos.error;


import org.springframework.http.HttpStatus;

public enum ErrorType {
    USER_ALREADY_REGISTERED_OTHER(HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_IS_NOT_AVAILABLE(HttpStatus.INTERNAL_SERVER_ERROR),
    USER_RESTRICTED (HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    DATA_CONFLICT(HttpStatus.CONFLICT),
    UNABLE_ADD_ROLE(HttpStatus.INTERNAL_SERVER_ERROR),
    UNABLE_REMOVE_ROLE(HttpStatus.INTERNAL_SERVER_ERROR);

    HttpStatus httpStatus;

    ErrorType(HttpStatus httpStatus) {
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
