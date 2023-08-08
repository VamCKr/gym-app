package com.epam.gymapp.service;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.ModifyCredentialsRequest;
import com.epam.gymapp.model.User;

public interface UserService {

    String LOGIN_STRING = "login";
    String UPDATE_CREDENTIALS = "updateCredentials";

    void login(CredentialsDetails credentialsDetails);

    void updateCredentials(ModifyCredentialsRequest credentialsDetails);

    User getUser(String username);

    void removeUser(User user);
}
