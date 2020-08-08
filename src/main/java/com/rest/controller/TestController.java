package com.rest.controller;

import com.rest.model.MyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

//Controller
@RestController
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test")
    public String test() {
        return "This is just a test on: http://localhost:8089/test";
    }

    @GetMapping("/test-bean")
    public MyBean testBean() {
        return new MyBean("My Bean message!!!");
    }

    @GetMapping("/test-bean/path-variable/{name}")
    public MyBean testBeanWithPathVariable(@PathVariable String name) {
        return new MyBean(String.format("With Path variable my Bean message: %s", name));
    }

    //This method displays messages in different languages as mentioned.
    //On postman if the Accept-Language is default- its english, dn = denmark and fr = french
    //messages_fr.properties, messages_dn.properties & messages.properties files with messages are created under resources from which messages arec picked up
    //as mentioned by the user. We can test it through postman
    @GetMapping("/internationalized")
    public String helloInternationalized() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    //Following two Beans are for I18N (Internationalization)
    //For this under application.properties set [spring.messages.basename=messages]
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

}
