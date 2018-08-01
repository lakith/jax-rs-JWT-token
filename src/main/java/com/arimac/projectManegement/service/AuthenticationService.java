package com.arimac.projectManegement.service;

import com.arimac.projectManegement.model.User;

public class AuthenticationService {

    public User getUserByEmail(){
//        User = new User("lakith","lakith1995@gmail.com","Softwear Engineer","0342252011","gest","password","dsfdsfsdfasdsa","34");
         User user = new User("lakith","lakith1995@gmail.com","SE","034-2252011","gest","password","salt",2311);
         return user;
    }

}
