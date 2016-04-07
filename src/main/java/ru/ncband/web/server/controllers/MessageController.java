package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ncband.web.server.db.servises.MessageDB;
import ru.ncband.web.shared.classes.Messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
public class MessageController {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public Messages getMessages(@FormParam("date") String date){
        MessageDB messageDB = new MessageDB();
        return messageDB.getMessagesForDate(date);
    }
}
