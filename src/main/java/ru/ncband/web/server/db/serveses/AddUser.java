package ru.ncband.web.server.db.serveses;

import org.fusesource.restygwt.client.DirectRestService;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/main/user")
public interface AddUser extends DirectRestService {
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
