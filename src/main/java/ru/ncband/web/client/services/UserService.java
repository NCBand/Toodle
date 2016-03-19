package ru.ncband.web.client.services;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;
import ru.ncband.web.shared.Id;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserService extends RestService {
    class Instance {
        private static UserService instance;

        public static UserService getInstance() {
            if (instance == null) {
                instance = GWT.create(UserService.class);
            }
            Resource resource = new Resource(GWT.getModuleBaseURL() + "user");
            ((RestServiceProxy) instance).setResource(resource);
            return instance;
        }
    }

    @POST
    @Path("/setUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void setUser(@FormParam("firsname") String firstname,
                 @FormParam("lastname") String lastname,
                 @FormParam("login") String login,
                 @FormParam("mail") String mail,
                 @FormParam("password") String password,
                 @FormParam("age") String age,
                 @FormParam("sex") String sex,
                 MethodCallback<String> callback);

    @POST
    @Path("/getUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getUser(@FormParam("login") String login,
                 @FormParam("password") String password,
                 MethodCallback<Id> callback);
}
