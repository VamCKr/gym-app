package com.epam.gymapp.util;


import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CredentialGenerator {
    @Autowired
    SecureRandom random;

    @Autowired
    CredentialsDetails credentialsDetails;
    private String generateUsername(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        int randomNumber = random.nextInt(1000,9999);
        return firstName.toLowerCase() +'_'+ lastName.toLowerCase() +'_'+ randomNumber;
    }

    private String generatePassword() {
        int passwordLength = 8;
        String passwordChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";
        List<Character> password = new ArrayList<>();
        password.add(getRandomCharFromString("abcdefghijklmnopqrstuvwxyz"));
        password.add(getRandomCharFromString("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        password.add(getRandomCharFromString("0123456789"));
        password.add(getRandomCharFromString("!@#$%^&*-_=+|;:,.?"));

        for (int i = password.size(); i < passwordLength; i++) {
            int index = random.nextInt(passwordChars.length());
            password .add(passwordChars.charAt(index));
        }
        Collections.shuffle(password);
        return password.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private char getRandomCharFromString(String chars) {
        int index = random.nextInt(chars.length());
        return chars.charAt(index);
    }

    public CredentialsDetails generateCredentials(User user){
        credentialsDetails.setUserName(generateUsername(user));
        credentialsDetails.setPassword(generatePassword());
        return credentialsDetails;
    }
}
