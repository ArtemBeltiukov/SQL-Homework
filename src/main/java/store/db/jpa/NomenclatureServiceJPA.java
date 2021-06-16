package store.db.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import store.db.services.Service;
import store.model.Model;
import store.model.Nomenclature;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "enable", name = "jpa")
public class NomenclatureServiceJPA implements Service {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    EntityManager entityManager;

    @Override
    public int create(Model model) {
        if (!(model instanceof Nomenclature))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
        }
        return ((Nomenclature) model).getId();
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
        List<Model> nomenclatureList;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            nomenclatureList = session.createQuery("from Nomenclature").list();
            session.getTransaction().commit();
        }
        return nomenclatureList;
    }

    @Override
    public List<Nomenclature> getAllByCriteria() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Nomenclature> nomenclatureCriteria = cb.createQuery(Nomenclature.class);
        Root<Nomenclature> nomenclatureRoot = nomenclatureCriteria.from(Nomenclature.class);
        nomenclatureCriteria.select(nomenclatureRoot);
        List<Nomenclature> nomenclatureList = entityManager.createQuery(nomenclatureCriteria)
                .getResultList();
        return nomenclatureList;
    }
}
