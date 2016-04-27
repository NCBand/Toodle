package ru.ncband.web.server.db.servises;

import org.apache.commons.io.IOUtils;
import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public Task getTask(String id){
        /*try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            TasksEntity taskEntity = session.get(TasksEntity.class, Integer.parseInt(id));
            transaction.commit();

            Task task = new Task();
            task.setType(Integer.toString(taskEntity.getType()));
            task.setId(Integer.toString(taskEntity.getId()));

            List<String> strings = new ArrayList<String>();
            strings.add(taskEntity.getQuestion());

            if(!(Integer.toString(taskEntity.getType())).equals(LessonProperty.typeText())) {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM AnswersEntity where task=:param");
                query.setParameter("param", taskEntity.getId());
                List<AnswersEntity> answers = (List<AnswersEntity>) query.list();
                transaction.commit();

                List<String> answer_ids = new ArrayList<String>();

                for (AnswersEntity tmp:
                     answers) {
                    answer_ids.add(Integer.toString(tmp.getId()));
                    strings.add(tmp.getAnsw());
                }
                task.setAnswer_ids(answer_ids);
            }

            task.setTexts(strings);
            return task;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }*/
        return null;
    }
    
    public Lesson getLesson(String id){
        /*try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TasksEntity where lesson=:param");
            query.setParameter("param", Integer.parseInt(id));

            List<TasksEntity> tasks = (List<TasksEntity>) query.list();
            transaction.commit();

            Lesson lesson = new Lesson();
            lesson.setId(id);
            List<String> client_tasks = new ArrayList<String>();
            for (TasksEntity task:
                 tasks) {
                client_tasks.add(Integer.toString(task.getId()));
            }
            lesson.setTasks(client_tasks);

            return lesson;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }*/
        return null;
    }
    
    public Status check( Answer answer){
        /*try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM AnswersEntity");

            ArrayList<AnswersEntity> answers = (ArrayList<AnswersEntity>) query.list();

            Status status = new Status();
            transaction.commit();

            int right = 0;
            List<String> varient = answer.getAnswer();
            for (String tmp:
                 varient) {
                for (AnswersEntity base:
                     answers) {
                    if(tmp.equals(Integer.toString(base.getId())) && base.getRght() == 1){ //// TODO: 21.04.2016  
                       right++; 
                    }
                }
            }

            if(right == varient.size()){
                status.setMsg(BasicProperty.done());
            }else{
                status.setMsg(BasicProperty.fault());
            }
            return status;
        } catch (NullPointerException e){
            e.printStackTrace();
        }*/
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
            if(res == 0){
                return deleteTasks(id);
            }
            return new Status(BasicProperty.fault());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Status deleteTasks(String lesson_id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("from TasksEntity where lesson =:param");
            query.setParameter("param",Integer.parseInt(lesson_id));
            ArrayList<LessonsEntity> tasks = (ArrayList<LessonsEntity>) query.list();
            transaction.commit();

            for(LessonsEntity task:
                    tasks){
                if(!deleteAnswer(task.getId())){
                    return new Status(BasicProperty.fault());
                }
            }

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            query = session.createQuery("delete TasksEntity where lesson =:param");
            query.setParameter("param",Integer.parseInt(lesson_id));

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

    private boolean deleteAnswer(int task_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete AnswersEntity where task =:param");
            query.setParameter("param",task_id);

            int res = query.executeUpdate();
            transaction.commit();
            return res != 0;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
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

    public Status save(String name, String id){
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            LessonsEntity lessonsEntity = session.get(LessonsEntity.class, Integer.parseInt(id));
            lessonsEntity.setName(name);

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
