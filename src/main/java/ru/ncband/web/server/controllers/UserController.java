package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ncband.web.server.db.serveses.UserDB;
import ru.ncband.web.shared.Id;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/toodle/user")
public class UserController {
    @RequestMapping(value = "/getUser", method = RequestMethod.POST , consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public Id getUser(@FormParam("login") String login,
                      @FormParam("password") String password){
        UserDB userDB = new UserDB();
        return userDB.get(login, password);
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public void setUser(@FormParam("firstname") String firstname,
                        @FormParam("lastname") String lastname,
                        @FormParam("login") String login,
                        @FormParam("mail") String mail,
                        @FormParam("password") String password,
                        @FormParam("age") String age,
                        @FormParam("sex") String sex){
        UserDB userDB = new UserDB();
        userDB.setUser(firstname, lastname, login, mail, password, age, sex);
    }
}
