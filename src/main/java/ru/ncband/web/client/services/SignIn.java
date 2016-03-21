package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface SignIn extends RestService {
    @POST
    @Path("/user/sign_in")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void signIn(@FormParam("login") String login,
                @FormParam("password") String password,
                MethodCallback<String> callback);

    @POST
    @Path("/user/registration")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void doRegistration(@RequestParam("form") String registration_string,
                        MethodCallback<String> callback);
}
