package ru.ncband.web.server.db.servises;

import com.google.gwt.regexp.shared.RegExp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.MessageEntity;
import ru.ncband.web.shared.classes.Messages;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.BasicProperty;

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

            if(date == null || !isValidDate(date)){
                throw new TypeNotPresentException(date, new Throwable());
            }

            Query query = session.createQuery("FROM MessageEntity where date =:param");
            query.setParameter("param", date);

            List<MessageEntity> messages = (List<MessageEntity>)query.list();
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

    public Status set(String date, String message){
        Status status = new Status();
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            MessageEntity entity = new MessageEntity();

            if (!isValidDate(date)) {
                return status; //// TODO: 21.04.2016
            }
            entity.setDate(date);
            entity.setText(message);

            session.save(entity);
            transaction.commit();
            status.setMsg(BasicProperty.done());
            return status;
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (TypeNotPresentException e){
            e.printStackTrace();
        }
        return status;
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

    private boolean isValidDate(String date){
        RegExp regExp = RegExp.compile("((0[1-9]{1})|(1[0-9]{1})|(2[0-9])|(3[01]))"+ BasicProperty.dateSeparator()+
                "((1[012]{1})|(0[1-9]{1}))" + BasicProperty.dateSeparator()+
                "([12]{1}[0-9]{3})");
        return regExp.test(date);
    }
}