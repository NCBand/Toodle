package ru.ncband.web.server.db.servises;

import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.shared.properties.LessonProperty;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.*;

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
        try {
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
        }
        return null;
    }
    
    public Lesson getLesson(String id){
        try {
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
        }
        return null;
    }
    
    public Status check( Answer answer){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM AnswersEntity where task =: param");
            query.setParameter("param", answer.getId());

            ArrayList<AnswersEntity> answers = (ArrayList<AnswersEntity>) query.list();

            Status status = new Status();
            transaction.commit();

            if(answers.get(answer.getAnswer()).getRght() == 1){
                status.setMsg(BasicProperty.done());
            }else{
                status.setMsg(BasicProperty.fault());
            }

            return status;
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
