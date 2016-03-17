package ru.ncband.web.client.services;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.ncband.web.shared.Id;

import javax.validation.constraints.Null;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/main/user")
public interface UserService extends RestService {
    @PUT
    @Path("{firstname}&{lastname}&{mail}&{login}&{password}&{age}&{sex}")
    void setUser(@PathParam("firstname") String firstname,
                 @PathParam("lastname") String lastname,
                 @PathParam("login") String login,
                 @PathParam("mail") String mail,
                 @PathParam("password") String password,
                 @PathParam("age") String age,
                 @PathParam("sex") String sex,
                 MethodCallback<Null> callback);

    @GET
    @Path("{login}&{password}")
    void getUser(@PathParam("login") String login,
                 @PathParam("password") String password,
                 MethodCallback<Id> callback);
}
