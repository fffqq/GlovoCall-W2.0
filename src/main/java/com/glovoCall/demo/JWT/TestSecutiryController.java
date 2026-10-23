package com.glovoCall.demo.JWT;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecutiryController {

    @GetMapping("/protected")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("kurwa");
    }
}
