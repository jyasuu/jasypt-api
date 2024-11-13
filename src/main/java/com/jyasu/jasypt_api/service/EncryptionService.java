package com.jyasu.jasypt_api.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.stereotype.Service;


@Service
public class EncryptionService {

    // Method to encrypt text
    public String encrypt(String input, String password) {
        PBEStringEncryptor encryptor = createEncryptor(password);
        return encryptor.encrypt(input);
    }

    // Method to decrypt text
    public String decrypt(String input, String password) {
        PBEStringEncryptor encryptor = createEncryptor(password);
        return encryptor.decrypt(input);
    }

    // Create encryptor with specific settings
    private PBEStringEncryptor createEncryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64"); // You can change this if you prefer other formats

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setConfig(config);
        return encryptor;
    }
}
