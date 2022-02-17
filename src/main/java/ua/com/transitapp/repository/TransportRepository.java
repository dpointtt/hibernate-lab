package ua.com.transitapp.repository;

import org.hibernate.SessionFactory;
import ua.com.transitapp.dao.TransportDao;
import ua.com.transitapp.entity.Transport;

import javax.persistence.EntityManager;
import java.util.List;

public class TransportRepository implements TransportDao {

    private final SessionFactory sessionFactory;

    public TransportRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Transport Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Transport Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Transport> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Transport> transportList = entityManager.createQuery("select t from Transport as t", Transport.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return transportList;
    }

    @Override
    public Transport findById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Transport transport = entityManager.find(Transport.class, Id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return transport;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Transport").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Transport as t where t.id = ?1")
                .setParameter(1, Id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Transport Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(Item) ? Item : entityManager.merge(Item));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
