package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.Id;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/main/user")
public interface UserService extends RestService {
    @POST
    @Path("/setUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void setUser(@FormParam("firsname") String firstname,
                 @FormParam("lastname") String lastname,
                 @FormParam("login") String login,
                 @FormParam("mail") String mail,
                 @FormParam("password") String password,
                 @FormParam("age") String age,
                 @FormParam("sex") String sex,
                 MethodCallback<String> callback);

    @POST
    @Path("/getUser")
    void getUser(@FormParam("login") String login,
                 @FormParam("password") String password,
                 MethodCallback<Id> callback);
}
