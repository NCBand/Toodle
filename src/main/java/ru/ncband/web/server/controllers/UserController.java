package ru.ncband.web.server.controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import ru.ncband.web.server.db.servises.UserDB;
import ru.ncband.web.shared.Id;
import ru.ncband.web.shared.Registration;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@RestController
public class UserController {
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String getUser(@FormParam("login") String login,
                                        @FormParam("password") String password){
        UserDB userDB = new UserDB();
        Id res = userDB.get(login, password);
        System.out.println(res.getId());
        return "done";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String setUser(@RequestParam("form") String registration_string) throws IOException {
        Registration registration  = new ObjectMapper().readValue(registration_string, Registration.class);
        UserDB userDB = new UserDB();
        userDB.set(registration);
        return "done";
    }
    }

