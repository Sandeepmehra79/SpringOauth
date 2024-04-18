package com.userService.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("home")
public class HomePageController {
    @GetMapping("admin")
    public ResponseEntity<String> admin(Principal principal) {
        return ResponseEntity.ok("Admin home page.. The logged in user is "+ principal.getName());
    }

    @GetMapping("user")
    public ResponseEntity<String> user(Principal principal) {
        return ResponseEntity.ok("User home page. The logged in user is "+ principal.getName());
    }

    @GetMapping("support")
    public ResponseEntity<String> support(Principal principal) {
        return ResponseEntity.ok("Support home page. The logged in user is "+ principal.getName());
    }

    @GetMapping("all")
    public ResponseEntity<String> all(Principal principal) {
        return ResponseEntity.ok("Home page for all. The logged in user is "+ principal.getName());
    }


}
