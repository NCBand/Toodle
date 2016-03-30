package ru.ncband.web.server.db.servises;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ncband.web.server.classes.Id;
import ru.ncband.web.server.db.HibernateSessionFactory;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Generator;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.classes.Registration;
import ru.ncband.web.shared.classes.Status;

import java.util.List;

public class UserDB {
    private SessionFactory sessionFactory;

    public UserDB(){
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    public Id get(String login, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity where login =:param");
            query.setParameter("param",login);

            List<UserEntity> users = query.list();
            for (UserEntity user :
                    users) {
                String salting = Salt.salting(user.getSalt().toString(), password);
                if (salting.equals(user.getPassword())) {
                    return new Id(Integer.toString(user.getId()));
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return new Id("-1");
    }


    public Status set(Registration registration) {
        try {
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
            String id = generator.createNum(11);

            person.setId(Integer.getInteger(id));
            person.setLogin(registration.getLogin());
            person.setPassword(salting);
            person.setSalt(Integer.getInteger(salt));

            session.save(person);
            return new Status("done");
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return new Status("fault");
    }
}
