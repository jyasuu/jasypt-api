package com.jyasu.jasypt_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jyasu.jasypt_api.service.EncryptionService;


@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    // Endpoint for encryption
    @PostMapping("/encrypt")
    public String encrypt(@RequestParam String input,@RequestParam String password) {
        return encryptionService.encrypt(input,password);
    }

    // Endpoint for decryption
    @PostMapping("/decrypt")
    public String decrypt(@RequestParam String input,@RequestParam String password) {
        return encryptionService.decrypt(input,password);
    }
}
