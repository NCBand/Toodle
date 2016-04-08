package ru.ncband.web.server.db.servises;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.MessageEntity;
import ru.ncband.web.server.logic.MessageCreator;
import ru.ncband.web.shared.classes.Messages;

import java.util.ArrayList;
import java.util.List;

public class MessageDB{
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public MessageDB() {
    }

    public Messages getMessagesForDate(String date){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            if(date == null || !MessageCreator.isValidDate(date)){
                throw new TypeNotPresentException(date, new Throwable());
            }

            Query query = session.createQuery("FROM MessageEntity where date =:param");
            query.setParameter("param", date);

            List<MessageEntity> messages = query.list();
            Messages send = new Messages();
            send.setMessages(convertEntity(messages));

            transaction.commit();
            return send;
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (TypeNotPresentException e){
            e.printStackTrace();
        }
        return null;
    }

    private List<String> convertEntity(List<MessageEntity> entities){
        List<String> list = new ArrayList<String>();

        if(entities == null || entities.isEmpty()){
            list.add("No message today.");
        }else{
            for (MessageEntity entity:
                 entities) {
                list.add(entity.getText());
            }
        }

        return list;
    }

}