package com.hexaware.frameworks.api;

public class TestData {
    String action;
    String data;
    String parameters;
    String status;
    String request;
    String response;
    String description;

    public TestData() {
    }



    public TestData(String action, String data, String parameters, String status, String request, String response, String description) {
        this.action = action;
        this.data = data;
        this.parameters = parameters;
        this.status = status;
        this.request = request;
        this.response = response;
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        return "TestData{" +
                ", Action='" + action + '\'' +
                ", Data='" + data + '\'' +
                ", Parameters='" + parameters + '\'' +
                ", Status='" + status + '\'' +
                ", Request='" + request + '\'' +
                ", Response='" + response + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }

}
