package ru.ncband.web.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ncband.web.server.db.serveses.UserDB;
import ru.ncband.web.shared.Id;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Controller
public class DBController {
    @RequestMapping(value = "/main/user/getUser", method = RequestMethod.POST , consumes = MediaType.APPLICATION_FORM_URLENCODED, produces = MediaType.APPLICATION_JSON)
    public Id getUser(@PathParam("login") String login,
                      @PathParam("password") String password){
        UserDB userDB = new UserDB();
        return userDB.get(login, password);
    }

    @RequestMapping(value = "/main/user/setUser", method = RequestMethod.POST)
    public void setUser(@PathParam("firstname") String firstname,
                 @PathParam("lastname") String lastname,
                 @PathParam("login") String login,
                 @PathParam("mail") String mail,
                 @PathParam("password") String password,
                 @PathParam("age") String age,
                 @PathParam("sex") String sex){
        UserDB userDB = new UserDB();
        userDB.setUser(firstname, lastname, login, mail, password, age, sex);
    }

}
