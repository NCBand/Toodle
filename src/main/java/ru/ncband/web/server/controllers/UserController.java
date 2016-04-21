package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.*;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.servises.UserDB;
import ru.ncband.web.shared.classes.UserForm;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.RequestProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
                            @FormParam("password") String password,
                            HttpServletRequest request){
        UserDB userDB = new UserDB();
        Status status = new Status();
        Id id = userDB.get(login, password);

        if(id == null){
            status.setMsg(BasicProperty.fault());
            return status;
        }

        HttpSession session = request.getSession();
        session.setAttribute(RequestProperty.sessionName(), id);

        status.setMsg(BasicProperty.done());
        return status;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Status setUser(@RequestBody UserForm form) throws IOException {
        UserDB userDB = new UserDB();
        return userDB.set(form);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/sign_out", method = RequestMethod.POST)
    public Status sign_out(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(RequestProperty.sessionName());
        return new Status(BasicProperty.done());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Status edit(@RequestBody UserForm form,
                       HttpServletRequest request){
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();
        Id id = (Id)session.getAttribute(RequestProperty.sessionName());
        return userDB.update(form,id.getId());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Status delete(HttpServletRequest request){
        HttpSession session = request.getSession();
        Id id = (Id)session.getAttribute(RequestProperty.sessionName());
        UserDB userDB = new UserDB();
        return userDB.delete(id.getId());
    }
}

