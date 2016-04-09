package ru.ncband.web.server.db.servises;

import org.hibernate.*;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Answer;
import ru.ncband.web.shared.classes.Task;
import ru.ncband.web.shared.classes.TaskLabel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskDB {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public TaskDB(){}

    public TaskLabel getLabels(){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM TasksEntity");

            List<TasksEntity> tasks = (List<TasksEntity>) query.list();
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<Integer> ids = new ArrayList<Integer>();
            TaskLabel taskLabel = new TaskLabel();

            for (TasksEntity task :
                    tasks) {
                names.add(task.getName());
                ids.add(task.getId());
            }

            transaction.commit();
            taskLabel.setIds(ids);
            taskLabel.setLabels(names);

            return taskLabel;
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
            TasksEntity tasksEntity = session.get(TasksEntity.class, Integer.parseInt(id));

            Task task = new Task();
            ArrayList<String> texts = new ArrayList<String>();
            texts.add(tasksEntity.getQuestion());
            task.setType(tasksEntity.getType());
            task.setId(Integer.parseInt(id));

            if(tasksEntity.getType() != Property.typeText()) {
                Query query = session.createQuery("FROM AnswersEntity where taskId =: param");
                query.setParameter("param", tasksEntity.getId());

                List<AnswersEntity> answers = (List<AnswersEntity>) query.list();
                for (AnswersEntity answer:
                        answers){
                    texts.add(answer.getAnswer());
                }
            }
            transaction.commit();

            task.setTexts(texts);
            return task;
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
