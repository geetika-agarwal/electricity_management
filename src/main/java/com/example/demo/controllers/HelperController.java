package com.example.demo.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Helper;
import com.example.demo.repositories.HelperRepository;

@RestController
public class HelperController {
	
	@Autowired(required = true)
	HelperRepository helperRepository;
	
	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException(e);
        }
    }
	
	@GetMapping("/helper/add-helper")
	public void addHelper() {
		Helper helper1 = new Helper("helper1@gmail.com", "helper@1", "Helper 1");
		Helper helper2 = new Helper("helper2@gmail.com", "helper@2", "Helper 2");
		Helper helper3 = new Helper("helper3@gmail.com", "helper@3", "Helper 3");
		
		helperRepository.save(helper1);
		helperRepository.save(helper2);
		helperRepository.save(helper3);
	}
	
	@GetMapping("/helper/login")
	public void helperLogin(@RequestBody Helper helper) {
		if(helperRepository.findById(helper.getEmail()) != null) {
			if(helperRepository.findById(helper.getEmail()).get().getPassword().equals(helper.getPassword())) {
				System.out.println("Successful Login");
				
			} else {
				System.out.println("Wrong Password");
			}
		}
	}
}
