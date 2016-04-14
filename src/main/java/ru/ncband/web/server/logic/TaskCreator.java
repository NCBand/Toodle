package ru.ncband.web.server.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
import ru.ncband.web.server.db.classes.LessonsEntity;
import ru.ncband.web.server.db.classes.TasksEntity;
import ru.ncband.web.shared.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskCreator {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public static void main(String[] args) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        LessonsEntity task = new LessonsEntity();

        System.out.println("Lesson name:");
        int id = Generator.getInstance().createNumInt();
        task.setId(id);
        task.setName(reader.readLine());

        session.save(task);
        transaction.commit();
        addTask(id);
    }

    private static void printTypes() {
        System.out.print("Text - " + Property.typeText());
        System.out.print(", Test - " + Property.typeTest());
        System.out.println(", MultiTest - " + Property.typeMultiTest());
    }

    private static void addTask(int lesson_id) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer;
        do {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            TasksEntity task = new TasksEntity();

            System.out.println("Task name:");
            task.setName(reader.readLine());
            task.setLesson(lesson_id);

            printTypes();
            System.out.println("Print type:");
            int type = Integer.parseInt(reader.readLine());
            int id = Generator.getInstance().createNumInt();

            System.out.println("Print question/text:");
            task.setId(id);
            task.setQuestion(reader.readLine());
            task.setType(type);
            session.save(task);
            transaction.commit();

            if (type != Property.typeText()) {


                do {
                    session = sessionFactory.openSession();
                    transaction = session.beginTransaction();
                    AnswersEntity answersEntity = new AnswersEntity();
                    System.out.println("Answer:");
                    answersEntity.setAnswer(reader.readLine());
                    System.out.println("It is right (y - 1/n - 0):");
                    answersEntity.setRight(Integer.parseInt(reader.readLine()));
                    answersEntity.setTaskId(id);

                    session.save(answersEntity);

                    System.out.println("Add another answer (y/n):");
                    answer = reader.readLine();
                    transaction.commit();
                } while (answer.equals("y"));
            }
            System.out.println("Add another task module (y/n):");
            answer = reader.readLine();
        } while (answer.equals("y"));
        reader.close();
    }
}
