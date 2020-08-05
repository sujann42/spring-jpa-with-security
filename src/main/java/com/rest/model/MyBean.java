package com.rest.model;

import java.util.stream.Stream;

public class MyBean {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyBean(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("MyBean message = %s", message);
    }

}
