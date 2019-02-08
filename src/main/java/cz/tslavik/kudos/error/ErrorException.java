package cz.tslavik.kudos.error;

import com.microsoft.applicationinsights.TelemetryClient;

public class ErrorException extends RuntimeException {


    private ErrorType errorType;
    private String detail;

    public ErrorException(ErrorType errorType, String detail, TelemetryClient telemetryClient) {
        super(detail);
        this.setErrorType(errorType);
        this.setDetail(detail);
        telemetryClient.trackException(this);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
