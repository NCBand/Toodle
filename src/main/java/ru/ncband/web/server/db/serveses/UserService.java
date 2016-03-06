package ru.ncband.web.server.db.serveses;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ncband.web.server.db.classes.UserEntity;

import javax.annotation.Resource;
import java.util.List;

public class UserService {
    protected static Logger logger = Logger.getLogger("user-service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<UserEntity> getAll() {
        logger.debug("Retrieving all persons");

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserEntity");
        return query.list();
    }

    public UserEntity get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserEntity.class, id);
    }

    public List<UserEntity> get(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserEntity where login = "+login);
        return query.list();
    }

    public void add(UserEntity person) {
        logger.debug("Adding new person");

        Session session = sessionFactory.getCurrentSession();
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
