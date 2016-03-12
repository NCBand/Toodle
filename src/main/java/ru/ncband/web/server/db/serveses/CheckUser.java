package ru.ncband.web.server.db.serveses;

import org.fusesource.restygwt.client.DirectRestService;
import ru.ncband.web.shared.Id;

import javax.ws.rs.*;

@Path("/main/user")
public interface CheckUser extends DirectRestService {
    @GET
    @Path("{login}&{password}")
    Id get(@PathParam("login") String login,
           @PathParam("password") String password);
}
