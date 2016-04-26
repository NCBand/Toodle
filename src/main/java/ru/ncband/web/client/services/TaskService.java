package ru.ncband.web.client.services;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;
import ru.ncband.web.shared.classes.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface TaskService extends RestService {
    @POST
    @Path("/lesson/labels")
    @Produces(MediaType.APPLICATION_JSON)
    void getLabels(MethodCallback<LessonLabel> callback);

    @POST
    @Path("/lesson/get_task")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getTask(@FormParam("id") String id,
                        MethodCallback<Task> callback);

    @POST
    @Path("/lesson/get_lesson")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void getLesson(@FormParam("id") String id,
                   MethodCallback<Lesson> callback);

    @POST
    @Path("/lesson/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void check(Answer answer,
               MethodCallback<Status> callback);

    @POST
    @Path("/lesson/new_lesson")
    @Produces(MediaType.APPLICATION_JSON)
    void newLesson(MethodCallback<String> callback);

    @POST
    @Path("/lesson/new_task")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void newTask(@FormParam("id") String id,
                MethodCallback<String> callback);

    @POST
    @Path("/lesson/new_answer")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void newAnswer(@FormParam("id") String id,
                 MethodCallback<String> callback);

    @POST
    @Path("/lesson/save_lesson")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void saveLesson(@FormParam("name") String name,
                    @FormParam("id") String id,
                    MethodCallback<Status> callback);

    @POST
    @Path("/lesson/save_task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void saveTask(Task task,
                    MethodCallback<Status> callback);

    @POST
    @Path("/lesson/save_answer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    void saveAnswer(Answer answer,
                    MethodCallback<Status> callback);

    @POST
    @Path("/lesson/delete_lesson")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void delete(@FormParam("id") String id,
                MethodCallback<Status> callback);

    @POST
    @Path("/lesson/delete_task")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void deleteTask(@FormParam("id") String id,
                MethodCallback<Status> callback);

    @POST
    @Path("/lesson/delete_answer")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void deleteAnswer(@FormParam("id") String id,
                MethodCallback<Status> callback);

    @POST
    @Path("/lesson/id")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    void saveId(@FormParam("id") String id,
                    MethodCallback<Status> callback);
}
