package ru.ncband.web.server.controllers;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ncband.web.server.db.servises.TaskDB;
import ru.ncband.web.shared.classes.*;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.properties.RequestProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.Iterator;
import java.util.List;

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

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/new_lesson", method = RequestMethod.POST)
    public String newLesson(HttpServletRequest request){
        TaskDB taskDB = new TaskDB();
        return taskDB.newLesson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/new_task", method = RequestMethod.POST)
    public String newTask(@FormParam("id") String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.newTask(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/new_answer", method = RequestMethod.POST)
    public String newAnswer(@FormParam("id")String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.newAnswer(id);
    }

    @POST
    @RequestMapping(value = "/upload_task", method = RequestMethod.POST)
    public void uploadTask(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1 * 1024 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        TaskDB taskDB = new TaskDB();

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem fileitem:
                 items){
                taskDB.upload(fileitem.getName(),fileitem.getInputStream(), 1);
            }

        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @RequestMapping(value = "/upload_answer", method = RequestMethod.POST)
    public void uploadAnswer(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1 * 1024 * 1024);
        ServletFileUpload upload = new ServletFileUpload(factory);
        TaskDB taskDB = new TaskDB();

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem fileitem:
                 items){
                taskDB.upload(fileitem.getName(),fileitem.getInputStream(), 0);
            }

        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/delete_lesson", method = RequestMethod.POST)
    public Status delete(@FormParam("id")String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.delete(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/save_lesson", method = RequestMethod.POST)
    public Status saveLesson(@FormParam("name") String name,@FormParam("id") String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.save(name, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/save_task", method = RequestMethod.POST)
    public Status saveTask(@RequestBody Task task){
        TaskDB taskDB = new TaskDB();
        return taskDB.saveTask(task);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/save_answer", method = RequestMethod.POST)
    public Status saveAnswer(@RequestBody Answer task){
        TaskDB taskDB = new TaskDB();
        return taskDB.saveAnswer(task);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/delete_task", method = RequestMethod.POST)
    public Status deleteTask(@FormParam("id") String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.deleteTask(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/delete_answer", method = RequestMethod.POST)
    public Status deleteAnswer(@FormParam("id") String id){
        TaskDB taskDB = new TaskDB();
        return taskDB.deleteTask(id);
    }
}
