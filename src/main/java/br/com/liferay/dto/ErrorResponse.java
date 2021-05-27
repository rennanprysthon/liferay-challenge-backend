package br.com.liferay.dto;

public class ErrorResponse {

    private int status;
    private String error;
    private Long timestamp;

    public ErrorResponse(int status, String error, Long timestamp) {
        this.status = status;
        this.error = error;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
