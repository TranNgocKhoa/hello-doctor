package com.hellodoctor.testservice.controllers;

import com.hellodoctor.common.exceptions.ResourceNotFoundException;
import com.hellodoctor.testservice.entities.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        if(name.equals("NotFound")) {
            throw new ResourceNotFoundException();
        }
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
