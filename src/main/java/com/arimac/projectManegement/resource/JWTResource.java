package com.arimac.projectManegement.resource;

import com.arimac.projectManegement.model.Login;
import com.arimac.projectManegement.model.ResponceLogin;
import com.arimac.projectManegement.model.User;
import com.arimac.projectManegement.security.AuthHandling;
import com.arimac.projectManegement.service.AuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class JWTResource {

    AuthenticationService ser=new AuthenticationService();
    @GET
    public Response asd(){
        User log=ser.getUserByEmail();
        return Response.ok(log).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponceLogin login(Login login) {
        AuthHandling authHandling = new AuthHandling();
        return authHandling.loginUser(login);
    }


}
