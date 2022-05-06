package com.dcb.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

//@Component  // whenever spring application context starts it will add this class into the Container
@RestController     // Calls Controller which calls Component
//@PropertySource("classpath:application.properties")   // not required as only one default property file present
public class HelloController {

    @Value("${welcome.Message}")
    private String welcomeMessage;


    //@RequestMapping(value="/", method= RequestMethod.GET) // here '/'is the endpoint
    @GetMapping("/")
    public String helloMethod() {
        return welcomeMessage;
    }

    @GetMapping("/path-variable/{name}")
    public String helloMethod(@PathVariable String name) {
        return ("Hello World "+ name);
    }
}
