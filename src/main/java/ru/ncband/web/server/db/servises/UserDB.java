package ru.ncband.web.server.db.servises;

import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ncband.web.server.Id;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Salt;
import ru.ncband.web.shared.classes.Registration;

import javax.annotation.Resource;
import java.util.List;

public class UserDB {
    protected static Logger logger = Logger.getLogger("user-service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<UserEntity> getAll() {
        logger.debug("Retrieving all persons");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserEntity ");
        return query.list();
    }

    public UserEntity get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    public Id get(String login, String password) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("FROM UserEntity where login = "+login);

            List<UserEntity> users = query.list();
            for (UserEntity user :
                    users) {
                String salting = Salt.salting(user.getSalt(), password);
                if (salting.equals(user.getPassword())) {
                    return new Id(user.getId());
                }
            }
        } catch (NullPointerException e){
            logger.trace(e.getMessage());
        }
        
        return new Id(-1);
    }


    public void set(Registration registration) {
        logger.debug("Add person");

        Session session = sessionFactory.getCurrentSession();
        UserEntity person = new UserEntity();

        person.setFirstname(registration.getFirstname());
        person.setLastname(registration.getLastname());
        person.setAge(Integer.getInteger(registration.getAge()));
        person.setMail(registration.getMail());
        person.setSex(registration.getSex());

        Integer salt = 0; //// TODO: 12.03.2016  
        String salting = Salt.salting(salt,registration.getPassword());
        Integer id = 0;
        
        person.setId(id);
        person.setLogin(registration.getLogin());
        person.setPassword(salting);
        person.setSalt(salt);
        
        session.save(person);
    }

    public void delete(Integer id) {
        logger.debug("Deleting existing person");

        Session session = sessionFactory.getCurrentSession();
        UserEntity person = session.get(UserEntity.class, id);
        session.delete(person);
    }

    public void edit(UserEntity person) {
        logger.debug("Editing existing person");

        Session session = sessionFactory.getCurrentSession();
        UserEntity existingPerson = session.get(UserEntity.class, person.getId());
        session.save(existingPerson);
    }
}
