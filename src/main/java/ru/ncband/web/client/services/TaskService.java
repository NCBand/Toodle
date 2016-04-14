package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Lesson;
import ru.ncband.web.shared.classes.LessonLabel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface TaskService extends RestService {
    @POST
    @Path("/task/labels")
    @Produces(MediaType.APPLICATION_JSON)
    void getLabels(MethodCallback<LessonLabel> callback);

    @POST
    @Path("/task/get_task")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getTask(@FormParam("id") String id,
                        MethodCallback<Lesson> callback);

    @POST
    @Path("/task/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void check(Answer answer,
                    MethodCallback<Answer> callback);
}
