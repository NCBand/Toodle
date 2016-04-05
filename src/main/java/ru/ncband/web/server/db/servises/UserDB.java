package ru.ncband.web.server.db.servises;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.hibernate.transform.Transformers;
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
    private static SessionFactory sessionFactory;

    public UserDB(){
        if(sessionFactory == null) {
            sessionFactory = HibernateSessionFactory.getSessionFactory();
        }
    }

    public Id get(String login, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity where login =:param");
            query.setParameter("param",login);

            List<UserEntity> users = (List<UserEntity>) query.setResultTransformer(Transformers.aliasToBean(UserEntity.class)).list();
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
            person.setSex(registration.getSex());

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
        } catch (QuerySyntaxException e){
        e.printStackTrace();
        }
        return new Status(Property.fault());
    }
}
