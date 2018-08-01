package com.arimac.projectManegement.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.mail.internet.InternetAddress;
import java.util.Calendar;
import java.util.Date;

public class Validation {

    public boolean isValidEmail(String email) {
        try {
            new InternetAddress(email).validate();
        } catch (javax.mail.internet.AddressException e) {
            return false;
        }
        return true;
    }

    public String createJWT(String userId, String key, String role) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        JwtBuilder builder = Jwts.builder()
                .setId(userId)
                .setIssuedAt(new Date())
                .setSubject(role)
                .signWith(
                        SignatureAlgorithm.HS256, key)
                .setExpiration(c.getTime());
        return builder.compact();
    }

    //Fill the following method
  /*  public String getEncPassword(String password, String salt) {

    }*/

}
