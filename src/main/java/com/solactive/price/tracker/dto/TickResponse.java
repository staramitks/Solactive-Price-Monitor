package com.solactive.price.tracker.dto;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class TickResponse {

    private HttpStatus statusCode;
    private String message;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TickResponse that = (TickResponse) o;
        return Objects.equals(statusCode, that.statusCode) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message);
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public TickResponse setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TickResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
