package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import ru.ncband.web.shared.classes.Messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface MessageService {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getMessages(@FormParam("date") String date,
                     MethodCallback<Messages> messageMethodCallback);
}
