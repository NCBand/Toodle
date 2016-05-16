package ru.ncband.web.server.db.servises;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskDB {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    private static String imageDir = File.separator+"image"+File.separator;
    private static String url = File.separator+"lesson"+File.separator+"image"+File.separator;

    public TaskDB(){}

    public LessonLabel getLabels(){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM LessonsEntity");

            List<LessonsEntity> tasks = (List<LessonsEntity>) query.list();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> ids = new ArrayList<String>();
            LessonLabel lessonLabel = new LessonLabel();

            for (LessonsEntity task :
                    tasks) {
                names.add(task.getName());
                ids.add(Integer.toString(task.getId()));
            }

            transaction.commit();
            lessonLabel.setIds(ids);
            lessonLabel.setLabels(names);

            return lessonLabel;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Lesson getLesson(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            LessonsEntity entity = session.get(LessonsEntity.class, Integer.parseInt(id));
            transaction.commit();

            Lesson lesson = new Lesson();
            lesson.setId(id);
            lesson.setName(entity.getName());
            lesson.setTasks(getTasks(id));

            return lesson;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Task> getTasks(String lesson_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from TasksEntity where lesson =:param");
            query.setParameter("param",Integer.parseInt(lesson_id));

            List<TasksEntity> answersEntities = query.list();
            transaction.commit();

            ArrayList<Task> tasks = new ArrayList<Task>();
            for (TasksEntity entity:
                 answersEntities) {
                Task task = new Task();
                task.setId(Integer.toString(entity.getId()));
                task.setQuestion(entity.getQuestion());
                task.setType(Integer.toString(entity.getType()));
                task.setAnswers(getAnswers(entity.getId()));

                if(entity.getTaskImage() != null) {
                    task.setImage(getURL(entity.getTaskImage()));
                }
                tasks.add(task);
            }

            return tasks;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Answer> getAnswers(int task_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from AnswersEntity where task =:param");
            query.setParameter("param",task_id);

            List<AnswersEntity> answersEntities = query.list();
            transaction.commit();

            ArrayList<Answer> answers = new ArrayList<Answer>();
            for(AnswersEntity entity: answersEntities){
                Answer answer = new Answer();
                answer.setRight(Integer.toString(entity.getRght()));
                answer.setId(Integer.toString(entity.getId()));

                if(entity.getAnswImg() != null) {
                    answer.setImage(getURL(entity.getAnswImg()));
                }
                answer.setAnswer(entity.getAnsw());
                answers.add(answer);
            }

            return answers;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Status check(Answers answers){
        try {
            Iterator<String> ids = answers.getIds().iterator();
            Iterator<String> rights = answers.getRights().iterator();

            while (ids.hasNext()) {
                String id = ids.next();
                String answer = rights.next();

                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                AnswersEntity answersEntity = session.get(AnswersEntity.class, Integer.parseInt(id));
                transaction.commit();

                if(!answer.equals(Integer.toString(answersEntity.getRght()))){
                    return new Status(BasicProperty.fault());
                }
            }

            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String newLesson(){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            LessonsEntity lesson = new LessonsEntity();
            Generator generator = Generator.getInstance();

            lesson.setId(generator.createNumInt());
            session.save(lesson);
            transaction.commit();

            return Integer.toString(lesson.getId());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String newTask(String lesson_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Generator generator = Generator.getInstance();
            TasksEntity tasksEntity = new TasksEntity();
            tasksEntity.setLesson(Integer.parseInt(lesson_id));
            tasksEntity.setId(generator.createNumInt());

            session.save(tasksEntity);
            transaction.commit();

            return Integer.toString(tasksEntity.getId());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String newAnswer(String task_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Generator generator = Generator.getInstance();
            AnswersEntity answersEntity = new AnswersEntity();
            answersEntity.setTask(Integer.parseInt(task_id));
            answersEntity.setId(generator.createNumInt());

            session.save(answersEntity);
            transaction.commit();

            return Integer.toString(answersEntity.getId());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status delete(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete LessonsEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("from TasksEntity where lesson =:param");
            query.setParameter("param",Integer.parseInt(id));

            List<TasksEntity> tasksEntities = query.list();
            transaction.commit();

            for (TasksEntity entity:
                     tasksEntities) {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                query = session.createQuery("delete AnswersEntity where task =:param");
                query.setParameter("param",entity.getId());

                res = query.executeUpdate();
                transaction.commit();
            }

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("delete TasksEntity where lesson =:param");
            query.setParameter("param",Integer.parseInt(id));

            res = query.executeUpdate();
            transaction.commit();
            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status deleteTask(String id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete TasksEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("delete AnswersEntity where task =:param");
            query.setParameter("param",Integer.parseInt(id));

            res = query.executeUpdate();
            transaction.commit();

            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status deleteAnswer(String id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete AnswersEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();

            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status save(Lesson lesson){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            LessonsEntity lessonsEntity = session.get(LessonsEntity.class, Integer.parseInt(lesson.getId()));
            lessonsEntity.setName(lesson.getName());

            session.update(lessonsEntity);
            transaction.commit();

            if(lesson.getTasks() == null){
                return new Status(BasicProperty.done());
            }
            for (Task task:lesson.getTasks()){
                if(!saveTask(task)){
                    return new Status(BasicProperty.fault());
                }
            }

            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    private boolean saveTask(Task task){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            TasksEntity entity = session.get(TasksEntity.class, Integer.parseInt(task.getId()));
            entity.setQuestion(task.getQuestion());
            entity.setType(Integer.parseInt(task.getType()));

            session.update(entity);
            transaction.commit();

            if(task.getAnswers() == null){
                return true;
            }

            for (Answer answer:task.getAnswers()){
                if(!saveAnswer(answer)){
                    return false;
                }
            }
            return true;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveAnswer(Answer answer){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            AnswersEntity entity = session.get(AnswersEntity.class, Integer.parseInt(answer.getId()));
            entity.setRght(Integer.parseInt(answer.getRight()));
            entity.setAnsw(answer.getAnswer());

            session.update(entity);
            transaction.commit();
            return true;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void upload(String id, FileItem fileItem, int type){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hash = saveFile(fileItem);
            if(type == 1) {
                TasksEntity entity = session.get(TasksEntity.class, Integer.parseInt(id));
                entity.setTaskImage(hash);
                session.update(entity);
            }else {
                AnswersEntity entity = session.get(AnswersEntity.class, Integer.parseInt(id));
                entity.setAnswImg(hash);
                session.update(entity);
            }

            transaction.commit();
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String saveFile(FileItem fileItem) throws IOException {
        String hash = Salt.sha3(fileItem.getName());

        String encode = fileItem.getContentType().substring(fileItem.getContentType().indexOf("/")+1);

        File dir = new File(System.getProperty("user.dir")+imageDir
                +hash.substring(0,10)+File.separator
                +hash.substring(10,20)+File.separator);
        if(!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(System.getProperty("user.dir")+imageDir
                            +hash.substring(0,10)+File.separator
                            +hash.substring(10,20)+File.separator
                            +hash.substring(20)+"."+encode);
        if(!file.exists()){
            file.createNewFile();
            InputStream in = fileItem.getInputStream();
            OutputStream out = new FileOutputStream(file);
            IOUtils.copy(in,out);
            in.close();
            out.close();
        }

        return hash+"."+encode;
    }

    public void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(!uri.contains("image")){
            return;
        }

        uri = uri.replace("/",File.separator);
        String path = uri.substring(uri.indexOf(imageDir));
        String encode = uri.substring(uri.indexOf(".")+1);
        response.setContentType("image/"+encode);

        File file = new File(System.getProperty("user.dir")+path);
        FileInputStream fileInputStream = new FileInputStream(file);
        response.setContentLength(new BigDecimal(file.length()).intValue());

        ServletOutputStream outStream = response.getOutputStream();
        IOUtils.copy(fileInputStream,outStream);
        outStream.close();
        fileInputStream.close();
    }

    private static String getURL(String hash){
        return  url+hash.substring(0,10)+File.separator
                +hash.substring(10,20)+File.separator
                +hash.substring(20);
    }
}
