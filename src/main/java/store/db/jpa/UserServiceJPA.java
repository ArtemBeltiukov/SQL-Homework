package store.db.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.db.services.Service;
import store.model.Model;
import store.model.Nomenclature;
import store.model.User;

import javax.persistence.EntityManager;
import java.util.List;


//@Component
public class UserServiceJPA implements Service {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    EntityManager entityManager;

    public List<User> getAllUsers() {
        List<User> userList;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from Users").list();
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public int create(Model model) {
        if (!(model instanceof User))
            throw new IllegalArgumentException("object isn`t User");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
        }
        return ((User) model).getId();
    }

    @Override
    public Model read(Model model) {
        return null;
    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void delete(Model model) {

    }

    @Override
    public List<Model> getAll() {
        List<Model> userList;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from Users").list();
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public List<Nomenclature> getAllByCriteria() {
        return null;
    }
}
