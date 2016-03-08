package ru.ncband.web.server.db.serveses;

import org.fusesource.restygwt.client.DirectRestService;
import ru.ncband.web.client.Id;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/main/user")
public interface UserServiceInt extends DirectRestService {
    @GET
    @Produces("application/json")
    @Path("{login}&{password}")
    Id get(@PathParam("login") String login, @PathParam("password") String password);
}
