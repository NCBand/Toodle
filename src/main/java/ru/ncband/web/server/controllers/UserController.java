package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncband.web.server.db.servises.UserDB;
import ru.ncband.web.shared.Id;
import ru.ncband.web.shared.Registration;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@RestController
public class UserController {
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody Id getUser(@FormParam("login") String login,
                                    @FormParam("password") String password){
        UserDB userDB = new UserDB();
        Id res = userDB.get(login, password);
        return res;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String setUser(Registration registration){
        UserDB userDB = new UserDB();
        userDB.set(registration);
        return "done";
    }
    }

