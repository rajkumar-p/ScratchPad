package com.model;

public class RequestJobDescription {
    String type;
    Credentials credentials;
    InputSettings inputSettings;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public InputSettings getInputSettings() {
        return inputSettings;
    }

    public void setInputSettings(InputSettings inputSettings) {
        this.inputSettings = inputSettings;
    }
}
