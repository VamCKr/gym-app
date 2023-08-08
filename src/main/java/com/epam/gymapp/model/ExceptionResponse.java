package com.epam.gymapp.model;

public class ExceptionResponse {
    String timestamp;
    String error;

    String path;

    String status;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String timestamp, String error, String path, String status) {
        this.timestamp = timestamp;
        this.error = error;
        this.path = path;
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public String getStatus() {
        return status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
