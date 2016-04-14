package ru.ncband.web.server.db.servises;

import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Lesson;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.classes.LessonLabel;

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
            ArrayList<Integer> ids = new ArrayList<Integer>();
            LessonLabel lessonLabel = new LessonLabel();

            for (LessonsEntity task :
                    tasks) {
                names.add(task.getName());
                ids.add(task.getId());
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

    public Lesson getTask(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TasksEntity where lesson=:param");
            query.setParameter("param", Integer.parseInt(id));

            List<TasksEntity> tasks = (List<TasksEntity>) query.list();
            transaction.commit();

            Lesson lesson = new Lesson();
            lesson.setId(Integer.parseInt(id));
            List<Task> client_task = new ArrayList<Task>();

            for (TasksEntity task:
                 tasks) {
                Task part = new Task();
                ArrayList<String> texts = new ArrayList<String>();
                texts.add(task.getQuestion());
                part.setType(task.getType());
                part.setId(task.getId());

                if(task.getType() != Property.typeText()) {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    query = session.createQuery("FROM AnswersEntity where taskId =:param");
                    query.setParameter("param", task.getId());

                    List<AnswersEntity> answers = (List<AnswersEntity>) query.list();
                    for (AnswersEntity answer:
                            answers){
                        texts.add(answer.getAnswer());
                    }
                    transaction.commit();
                }
                part.setTexts(texts);
                client_task.add(part);
            }

            lesson.setTasks(client_task);

            return lesson;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Answer check( Answer answer){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM AnswersEntity where taskId =: param");
            query.setParameter("param", answer.getId());

            Iterator<AnswersEntity> ans_int = ((List<AnswersEntity>) query.list()).iterator();
            ArrayList<Integer> res = new ArrayList<Integer>();

            for (Integer integer : answer.getAnswers()) {
                int ans = ans_int.next().getRight();
                int var = integer;

                res.add(new Integer(((ans + var) % 2 + 1) % 2));
            }
            transaction.commit();

            answer.setAnswers(res);
            return answer;
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
