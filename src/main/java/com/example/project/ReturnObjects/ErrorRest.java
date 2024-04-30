package com.example.project.ReturnObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorRest implements Serializable {
    @JsonProperty("Error")
    private String error;
    public ErrorRest(String Error) {
        this.error = Error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
