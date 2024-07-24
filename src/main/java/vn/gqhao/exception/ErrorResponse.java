package vn.gqhao.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public class ErrorResponse {
    private LocalDate dateTime;
    private int status;
    private String path;
    private String error;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDate dateTime, int status, String path, String error, String message) {
        this.dateTime = dateTime;
        this.status = status;
        this.path = path;
        this.error = error;
        this.message = message;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
