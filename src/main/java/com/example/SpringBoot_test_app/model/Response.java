package com.example.SpringBoot_test_app.model;

public class Response {

    private int statusCode;
    private String status;
    private String message;

    public static Response toModel(int statusCode, String status, String message) {
        Response model = new Response();
        model.setStatusCode(statusCode);
        model.setStatus(status);
        model.setMessage(message);
        return model;
    }
    
    public Response() {};

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
