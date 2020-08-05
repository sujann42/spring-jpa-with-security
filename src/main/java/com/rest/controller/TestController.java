package com.rest.controller;

import com.rest.model.MyBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class TestController {

    //This method just return a static message "This is just a test"
    @GetMapping("/test")
    public String test(){
        return "This is just a test on: http://localhost:8089/test";
    }

    @GetMapping("/test-bean")
    public MyBean testBean(){
        return new MyBean("My Bean message!!!");
    }

    @GetMapping("/test-bean/path-variable/{name}")
    public MyBean testBeanWithPathVariable(@PathVariable String name){
        return new MyBean(String.format("With Path variable my Bean message: %s", name));
    }
}
