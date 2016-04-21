package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.classes.Messages;
import ru.ncband.web.shared.classes.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface MessageService extends RestService {
    @POST
    @Path("/message/get_messages")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getMessages(@FormParam("date") String date,
                     MethodCallback<Messages> messageMethodCallback);


    @POST
    @Path("/message/set_message")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void setMessage(@FormParam("date") String date,
                    @FormParam("text") String message,
                    MethodCallback<Status> messageMethodCallback);
}
