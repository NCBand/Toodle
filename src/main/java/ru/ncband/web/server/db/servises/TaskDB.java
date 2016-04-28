package ru.ncband.web.server.db.servises;

import com.google.gwt.user.server.Base64Utils;
import org.apache.commons.io.IOUtils;
import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.*;
import ru.ncband.web.shared.properties.LessonProperty;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskDB {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

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

    public Answer getAnswer(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            AnswersEntity entity = session.get(AnswersEntity.class, Integer.parseInt(id));
            transaction.commit();

            Answer answer = new Answer();
            answer.setRight(Integer.toString(entity.getRght()));
            answer.setId(Integer.toString(entity.getId()));

            String image = "data:image/png;base64," + Base64Utils.toBase64(entity.getAnswImg());
            answer.setImage(image);
            answer.setAnswer(entity.getAnsw());

            return answer;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Task getTask(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            TasksEntity taskEntity = session.get(TasksEntity.class, Integer.parseInt(id));
            transaction.commit();

            Task task = new Task();
            task.setType(Integer.toString(taskEntity.getType()));
            task.setId(Integer.toString(taskEntity.getId()));
            task.setQuestion(taskEntity.getQuestion());

            String image = "data:image/png;base64," + Base64Utils.toBase64(taskEntity.getTaskImage());
            task.setImage(image);

            return task;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Ids getIds(String id, String type){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Ids ids = new Ids();
            List<String> strings = new ArrayList<String>();
            if(type.equals(LessonProperty.task())) {
                Query query = session.createQuery("FROM TasksEntity where lesson=:param");
                query.setParameter("param", Integer.parseInt(id));

                List<TasksEntity> tasks = (List<TasksEntity>) query.list();

                for (TasksEntity task:
                     tasks) {
                    strings.add(Integer.toString(task.getId()));
                }
            }else{
                Query query = session.createQuery("FROM AnswersEntity where task=:param");
                query.setParameter("param", Integer.parseInt(id));

                List<AnswersEntity> answers = (List<AnswersEntity>) query.list();
                for (AnswersEntity answer:
                     answers) {
                    strings.add(Integer.toString(answer.getId()));
                }
            }
            transaction.commit();

            ids.setIds(strings);
            return ids;
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
            if(res != 0){
                return new Status(BasicProperty.done());
            }
            return new Status(BasicProperty.fault());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status deleteTask(String id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete TasksEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();
            if(res != 0){
                return new Status(BasicProperty.done());
            }
            return new Status(BasicProperty.fault());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status deleteAnswer(String id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete AnswersEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();

            Status status = new Status(BasicProperty.fault());
            if(res != 0){
                status.setMsg(BasicProperty.done());
            }
            return status;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Status save(Lesson lesson){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            LessonsEntity lessonsEntity = session.get(LessonsEntity.class, Integer.parseInt(lesson.getId()));
            lessonsEntity.setName(lesson.getName());

            session.update(lessonsEntity);
            transaction.commit();
            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status saveTask(Task task){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            TasksEntity entity = session.get(TasksEntity.class, Integer.parseInt(task.getId()));
            entity.setQuestion(task.getQuestion());
            entity.setType(Integer.parseInt(task.getType()));

            session.update(entity);
            transaction.commit();
            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status saveAnswer(Answer answer){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            AnswersEntity entity = session.get(AnswersEntity.class, Integer.parseInt(answer.getId()));
            entity.setRght(Integer.parseInt(answer.getRight()));
            entity.setAnsw(answer.getAnswer());

            session.update(entity);
            transaction.commit();
            return new Status(BasicProperty.done());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public void upload(String id, InputStream stream, int type){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            if(type == 1) {
                TasksEntity entity = session.get(TasksEntity.class, Integer.parseInt(id));
                entity.setTaskImage(IOUtils.toByteArray(stream));
                session.update(entity);
            }else {
                AnswersEntity entity = session.get(AnswersEntity.class, Integer.parseInt(id));
                entity.setAnswImg(IOUtils.toByteArray(stream));
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
}
