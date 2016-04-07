package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.classes.Registration;
import ru.ncband.web.shared.classes.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface UserService extends RestService {
    @POST
    @Path("/user/sign_in")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void signIn(@FormParam("login") String login,
                @FormParam("password") String password,
                MethodCallback<Status> callback);

    @POST
    @Path("/user/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void doRegistration(Registration registration_form,
                        MethodCallback<Status> callback);

    @POST
    @Path("/user/sign_out")
    @Produces(MediaType.APPLICATION_JSON)
    void signOut(MethodCallback<Status> callback);

    @POST
    @Path("/user/settings")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void changeUserData(Registration registration_form,
                        MethodCallback<Status> callback);
}
