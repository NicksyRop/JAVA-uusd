package com.example.javaUSSD;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.*;
import static spark.Spark.*;
@RestController
public class UserController {


    @Autowired
    private  UserRepository userRepository;


    @GetMapping("/")
    public String home(){

        return  "USSD - java";
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {

        return userRepository.findAll();

    }
    @PostMapping("/ussd")
    public @ResponseBody String ussdCallback( @RequestParam String sessionId, @RequestParam String serviceCode,
                                              @RequestParam String phoneNumber, @RequestParam String text){

        StringBuilder response = new StringBuilder("");

        if (text.isEmpty()) {
            // This is the first request. Note how we start the response with CON

            response.append("CON What would you like to check\n1. My account\2. My phone number");

        } else if (text.contentEquals("1")) {
            // Business logic for first level response

            response.append("CON Choose account information you want to view\n1. Account number");

        } else if (text.contentEquals("2")) {
            // Business logic for first level response

            // This is a terminal request. Note how we start the response with END
            response.append("END Your phone number is ");
            response.append(phoneNumber);

        } else if (text.contentEquals("1*1")) {
            // This is a second level response where the user selected 1 in the first instance

            String accountNumber = "ACC100101";
            response.append("END Your account number is "); // This is a terminal request. Note how we start the response with END
            response.append(phoneNumber);

        }

        return response.toString();

    }


}
