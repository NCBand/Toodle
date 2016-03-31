package ru.ncband.web.server.db.servises;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.classes.Registration;
import ru.ncband.web.shared.classes.Status;

import java.util.List;

public class UserDB {
    public static Id get(String login, String password) {
        try {
            SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity where login =:param");
            query.setParameter("param",login);

            List<UserEntity> users = query.list();
            for (UserEntity user :
                    users) {
                String salting = Salt.salting(user.getSalt().toString(), password);
                if (salting.equals(user.getPassword())) {
                    Id id = new Id();
                    id.setId(Integer.toString(user.getId()));

                    String hash = Salt.sha3(Integer.toString(user.getId()));
                    id.setHash(hash);

                    return id;
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (QuerySyntaxException e){
            e.printStackTrace();
        }
        return null;
    }


    public static Status set(Registration registration) {
        try {
            SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            UserEntity person = new UserEntity();

            person.setFirstname(registration.getFirstname());
            person.setLastname(registration.getLastname());
            person.setAge(Integer.getInteger(registration.getAge()));
            person.setMail(registration.getMail());
            person.setSex(registration.getSex());

            Generator generator = Generator.getInstance();
            String salt = generator.createNum(11);
            String salting = Salt.salting(salt, registration.getPassword());

            person.setLogin(registration.getLogin());
            person.setPassword(salting);
            person.setSalt(Integer.getInteger(salt));

            session.save(person);
            return new Status("done");
        }catch(NullPointerException e){
            e.printStackTrace();
        } catch (QuerySyntaxException e){
        e.printStackTrace();
        }
        return new Status("fault");
    }
}
