package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.servises.UserDB;
import ru.ncband.web.shared.classes.Registration;
import ru.ncband.web.shared.classes.Status;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@RestController
public class UserController {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public Status sign_in(  @FormParam("login") String login,
                            @FormParam("password") String password){
        UserDB userDB = new UserDB();
        Status status = new Status();
        Id id = userDB.get(login, password);
        status.setMsg("done");
        return status;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Status setUser(@RequestBody Registration form) throws IOException {
        UserDB userDB = new UserDB();
        return userDB.set(form);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/sign_out", method = RequestMethod.POST)
    public Status sign_out(){
        return new Status("done");
    }
}

