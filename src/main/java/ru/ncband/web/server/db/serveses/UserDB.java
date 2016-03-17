package ru.ncband.web.server.db.serveses;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ncband.web.shared.Id;
import ru.ncband.web.server.db.classes.UserEntity;
import ru.ncband.web.server.logic.Salt;

import javax.annotation.Resource;
import javax.ws.rs.*;
import java.util.List;

public class UserDB {
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

    public Id get(String login, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM UserEntity where login = "+login);

        List<UserEntity> users = query.list();
        for (UserEntity user:
             users) {
            String salting = Salt.salting(user.getSalt(),password);
            if(salting.equals(user.getPassword())){
                return new Id(user.getId());
            }
        }
        
        return new Id(-1); //// TODO: 08.03.2016
    }


    public void setUser( String firstname,
                  String lastname,
                  String login,
                  String mail,
                  String password,
                  String age,
                  String sex) {
        logger.debug("Add person");

        Session session = sessionFactory.getCurrentSession();
        UserEntity person = new UserEntity();

        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setAge(Integer.getInteger(age));
        person.setMail(mail);
        person.setSex(sex);

        Integer salt = 0; //// TODO: 12.03.2016  
        String salting = Salt.salting(salt,password);
        Integer id = 0;
        
        person.setId(id);
        person.setLogin(login);
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
