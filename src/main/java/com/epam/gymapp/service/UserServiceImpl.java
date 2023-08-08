package com.epam.gymapp.service;

import com.epam.gymapp.customexceptions.UserException;
import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.ModifyCredentialsRequest;
import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.model.User;
import com.epam.gymapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public void login(CredentialsDetails credentialsDetails) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), LOGIN_STRING, this.getClass().getName(), credentialsDetails);
        User user = userRepository.findByUserName(credentialsDetails.getUserName()).orElseThrow(() -> new UserException("No Account with that username"));
        if (!user.getPassword().equals(credentialsDetails.getPassword())) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), LOGIN_STRING, this.getClass().getName());
            throw new UserException("wrong password");
        }
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), LOGIN_STRING);
    }

    @Override
    public void updateCredentials(ModifyCredentialsRequest credentialsDetails) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), UPDATE_CREDENTIALS, this.getClass().getName(), credentialsDetails);
        if (credentialsDetails.getOldPassword().equals(credentialsDetails.getNewPassword())) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_CREDENTIALS, this.getClass().getName());
            throw new UserException("Old password and new password should not be same");
        }

        if (! validatePasswordRequirements(credentialsDetails.getNewPassword())) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_CREDENTIALS, this.getClass().getName());
            throw new UserException("Password requirements not met with new password");
        }

        User user = userRepository.findByUserName(credentialsDetails.getUserName()).orElseThrow(() -> new UserException("No Account with that username"));

        if (! user.getPassword().equals(credentialsDetails.getOldPassword())) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_CREDENTIALS, this.getClass().getName());
            throw new UserException("Old password doesn't match");
        }

        user.setPassword(credentialsDetails.getNewPassword());
        userRepository.save(user);

        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), UPDATE_CREDENTIALS);
    }

    @Override
    public User getUser(String username) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "getUser", this.getClass().getName(), username);
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UserException("No user with that username"));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "getUser");
        return user;
    }


    private boolean validatePasswordRequirements(String password) {
        return password.length() >= 8 && !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase()) && password.matches(".*\\d.*") && !password.matches("[A-Za-z0-9]*");
    }


    @Override
    public void removeUser(User user) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "removeUser", this.getClass().getName(), user);
        userRepository.delete(user);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "removeUser");
    }
}

