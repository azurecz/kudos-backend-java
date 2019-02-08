package cz.tslavik.kudos.error;

import java.io.Serializable;

public class ErrorDTO implements Serializable {
    private String code;
    private String detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
