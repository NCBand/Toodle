package ru.ncband.web.server.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ncband.web.server.db.servises.TaskDB;
import ru.ncband.web.shared.classes.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
public class TaskController {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/labels", method = RequestMethod.POST)
    public LessonLabel getLabels(){
        TaskDB taskDB = new TaskDB();
        return taskDB.getLabels();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/get_task", method = RequestMethod.POST)
    public Task getTask(@FormParam("id")String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.getTask(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public Status checkAnswer(@RequestBody Answer answer){
        TaskDB taskDB = new TaskDB();
        return taskDB.check(answer);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/get_lesson", method = RequestMethod.POST)
    public Lesson getLesson(@FormParam("id")String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.getLesson(id);
    }
}
