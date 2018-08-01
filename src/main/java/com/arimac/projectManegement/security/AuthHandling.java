package com.arimac.projectManegement.security;

import com.arimac.projectManegement.Exeption.PMExpecption;
import com.arimac.projectManegement.Filter.RoleDetails.Roles;
import com.arimac.projectManegement.model.Login;
import com.arimac.projectManegement.model.ResponceLogin;
import com.arimac.projectManegement.model.User;
import com.arimac.projectManegement.service.AuthenticationService;

import java.util.UUID;

public class AuthHandling {

    public ResponceLogin loginUser(Login loginModel) {
        if (!new Validation().isValidEmail(loginModel.getEmail())) {
            throw new PMExpecption("Enter A Valied Email");
        }
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.getUserByEmail();
        if(user == null){
            throw new PMExpecption("Email Does Not Exists");
        }
        if(user.getPassword() != null){
            //Encrypt the password here
            //loginModel.getPassword() -> Encrypted Password
            if(!loginModel.getPassword().equals(user.getPassword())){
                throw new PMExpecption("Incorrect password");
            }
        }
        ResponceLogin responceLogin = new ResponceLogin();
        //Convert this User model id to a valied (i mean a true) ID
        responceLogin.setUserId(Integer.toString(user.getUser_id()));
        responceLogin.setSecretKey(UUID.randomUUID().toString().replaceAll("-", ""));
        String token = new Validation().createJWT(Integer.toString(user.getUser_id()),responceLogin.getSecretKey(),Roles.GUEST);
        responceLogin.setToken(token);
        return responceLogin;
        }
}
