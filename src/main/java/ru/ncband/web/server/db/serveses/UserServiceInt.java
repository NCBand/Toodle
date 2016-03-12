package ru.ncband.web.server.db.serveses;

import org.fusesource.restygwt.client.DirectRestService;
import ru.ncband.web.shared.Id;

import javax.ws.rs.*;

@Path("/main/user")
public interface UserServiceInt extends DirectRestService {
    @GET
    @Path("{login}&{password}")
    Id get(@PathParam("login") String login,
           @PathParam("password") String password);

    @PUT
    @Path("{firstname}&{lastname}&{mail}&{login}&{password}&{age}&{sex}")
    void setUser(@PathParam("firstname") String firstname,
                 @PathParam("lastname") String lastname,
                 @PathParam("login") String login,
                 @PathParam("mail") String mail,
                 @PathParam("password") String password,
                 @PathParam("age") String age,
                 @PathParam("sex") String sex);
}
