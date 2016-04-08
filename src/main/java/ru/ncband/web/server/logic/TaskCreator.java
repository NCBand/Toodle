package ru.ncband.web.server.logic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.AnswersEntity;
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
        TasksEntity task = new TasksEntity();

        printTypes();
        System.out.print("Print type: ");
        int type = Integer.parseInt(reader.readLine());
        session.save(task);

        if(type != Property.typeTest()){
            String answer;

            do {
                AnswersEntity answersEntity = new AnswersEntity();
                System.out.print("Answer: ");
                answersEntity.setAnswer(reader.readLine());
                System.out.print("It is right (y - 1/n - 0): ");
                answersEntity.setRight(Integer.parseInt(reader.readLine()));
                answersEntity.setTaskId(task.getId());

                session.save(answersEntity);

                System.out.print("Add another answer (y/n): ");
                answer = reader.readLine();
            }while(answer.equals("y"));
        }

        transaction.commit();
        reader.close();
    }

    private static void printTypes(){
        System.out.println("Text - "+Property.typeText());
        System.out.println("Test - "+Property.typeTest());
        System.out.println("MultiTest - "+Property.typeMultiTest());
    }
}
