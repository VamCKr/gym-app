package com.epam.gymapp.controller;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.ModifyCredentialsRequest;
import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@Valid @RequestBody CredentialsDetails credentialsDetails) {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "login", this.getClass().getName(), credentialsDetails);
        userService.login(credentialsDetails);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "login");
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> changeLogin(@Valid @RequestBody ModifyCredentialsRequest modifyCredentialsDetails) {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "changeLogin", this.getClass().getName(), modifyCredentialsDetails);
        userService.updateCredentials(modifyCredentialsDetails);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "changeLogin");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
