package ru.ncband.web.client.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.Id;

@Path("/main/user")
public interface UserService extends RestService {
    @GET
    @Path("{login}&{password}")
    void getUser(@PathParam("login") String login, @PathParam("password") String password ,MethodCallback<Id> callback);


}
