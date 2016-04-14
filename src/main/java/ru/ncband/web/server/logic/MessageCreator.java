package ru.ncband.web.server.logic;

import com.google.gwt.regexp.shared.RegExp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.MessageEntity;
import ru.ncband.web.shared.Property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageCreator {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public static void main(String[] args) throws IOException {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        MessageEntity entity = new MessageEntity();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Print date for message (MM"+Property.dateSeparator()+
                                                "DD"+Property.dateSeparator()
                                                +"YYYY): ");
        String date = reader.readLine();
        if(!isValidDate(date)){
            throw new TypeNotPresentException(date, new Throwable());
        }
        entity.setDate(date);

        System.out.println("Message:");
        String text = reader.readLine();
        entity.setText(text);

        session.save(entity);
        transaction.commit();
        reader.close();
    }

    public static boolean isValidDate(String date){
        RegExp regExp = RegExp.compile("((0[1-9]{1})|(1[0-9]{1})|(2[0-9])|(3[01]))"+Property.dateSeparator()+
                                        "((1[012]{1})|(0[1-9]{1}))" +Property.dateSeparator()+
                                        "([12]{1}[0-9]{3})");
        return regExp.test(date);
    }
}
