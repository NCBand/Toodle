package ru.ncband.web.server.db.servises;

import org.hibernate.*;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.classes.UserForm;
import ru.ncband.web.shared.properties.BasicProperty;
import ru.ncband.web.shared.classes.Status;

import java.util.List;

public class UserDB {
    private static SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    public UserDB(){}

    public Id get(String login, String password) {
        if(login == null || password == null || login.equals("") || password.equals("")){
            return null;
        }

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


    public Status set(UserForm userForm) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            UserEntity person = new UserEntity();

            person.setFirstname(userForm.getFirstname());
            person.setLastname(userForm.getLastname());
            person.setAge(Integer.getInteger(userForm.getAge()));
            person.setMail(userForm.getMail());
            person.setSex(userForm.getSex()); //// TODO: 05.04.2016

            Generator generator = Generator.getInstance();
            int salt = generator.createNumInt();
            String salting = Salt.salting(Integer.toString(salt), userForm.getPassword());

            person.setLogin(userForm.getLogin());
            person.setPassword(salting);
            person.setSalt(salt);

            session.save(person);
            transaction.commit();

            return new Status(BasicProperty.done());
        }catch(NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status delete(String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete UserEntity where id =:param");
            query.setParameter("param",Integer.parseInt(id));

            int res = query.executeUpdate();
            transaction.commit();
            if(res == 0){
                return new Status(BasicProperty.done());
            }
            return new Status(BasicProperty.fault());
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }

    public Status update(UserForm userForm, String id){
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, Integer.parseInt(id));

            if(userForm.getAge() != null){
                user.setAge(Integer.parseInt(userForm.getAge()));
            }
            if(userForm.getMail() != null){
                user.setMail(userForm.getMail());
            }
            if(userForm.getLastname() != null){
                user.setLastname(userForm.getLastname());
            }
            if(userForm.getFirstname() != null){
                user.setFirstname(userForm.getFirstname());
            }
            if(userForm.getLogin() != null){
                user.setLogin(userForm.getLogin());
            }
            if(userForm.getSex() != null){
                user.setSex(userForm.getSex());
            }
            if(userForm.getPassword() != null){
                String salting = Salt.salting(user.getSalt().toString(), userForm.getPassword());
                user.setPassword(salting);
            }

            session.update(user);
            transaction.commit();

        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return new Status(BasicProperty.fault());
    }
}
