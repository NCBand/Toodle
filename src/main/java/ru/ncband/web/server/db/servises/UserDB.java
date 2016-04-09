package ru.ncband.web.server.db.servises;

import org.hibernate.*;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.Property;
import ru.ncband.web.shared.classes.Registration;
import ru.ncband.web.shared.classes.Status;

import java.util.List;

public class UserDB {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public UserDB(){}

    public Id get(String login, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity where login =:param");
            query.setParameter("param",login);

            List<UserEntity> users = (List<UserEntity>) query.list();
            for (UserEntity user :
                    users) {
                String salting = Salt.salting(user.getSalt().toString(), password);
                if (salting.equals(user.getPassword())) {
                    Id id = new Id();
                    id.setId(Integer.toString(user.getId()));

                    String hash = Salt.sha3(Integer.toString(user.getId()));
                    id.setHash(hash);

                    transaction.commit();

                    return id;
                }
            }

            transaction.commit();

        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Status set(Registration registration) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            UserEntity person = new UserEntity();

            person.setFirstname(registration.getFirstname());
            person.setLastname(registration.getLastname());
            person.setAge(Integer.getInteger(registration.getAge()));
            person.setMail(registration.getMail());
            person.setSex(registration.getSex().substring(0,1)); //// TODO: 05.04.2016

            Generator generator = Generator.getInstance();
            int salt = generator.createNumInt();
            String salting = Salt.salting(Integer.toString(salt), registration.getPassword());

            person.setLogin(registration.getLogin());
            person.setPassword(salting);
            person.setSalt(salt);

            session.save(person);
            transaction.commit();

            return new Status(Property.done());
        }catch(NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(Property.fault());
    }

    public Status delete(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete UserEntity where id =:param");
            query.setParameter("param",id);

            int res = query.executeUpdate();
            transaction.commit();
            if(res == 0){
                return new Status(Property.done());
            }
            return new Status(Property.fault());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(Property.fault());
    }

    public Status update(Registration registration, String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, Integer.parseInt(id));

            if(registration.getAge() != null){
                user.setAge(Integer.parseInt(registration.getAge()));
            }
            if(registration.getMail() != null){
                user.setMail(registration.getMail());
            }
            if(registration.getLastname() != null){
                user.setLastname(registration.getLastname());
            }
            if(registration.getFirstname() != null){
                user.setFirstname(registration.getFirstname());
            }
            if(registration.getLogin() != null){
                user.setLogin(registration.getLogin());
            }
            if(registration.getSex() != null){
                user.setSex(registration.getSex().substring(0,1));
            }
            if(registration.getPassword() != null){
                String salting = Salt.salting(user.getSalt().toString(), registration.getPassword());
                user.setPassword(salting);
            }

            session.update(user);
            transaction.commit();

        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(Property.fault());
    }
}
