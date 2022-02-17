package ua.com.transitapp.repository;

import org.hibernate.SessionFactory;
import ua.com.transitapp.dao.StopDao;
import ua.com.transitapp.entity.Stop;

import javax.persistence.EntityManager;
import java.util.List;

public class StopRepository implements StopDao {

    private final SessionFactory sessionFactory;

    public StopRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Stop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Stop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Stop> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Stop> stopList = entityManager.createQuery("select s from Stop as s", Stop.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return stopList;
    }

    @Override
    public Stop findById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Stop stop = entityManager.find(Stop.class, Id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return stop;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Stop").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Stop as s where s.id = ?1")
                .setParameter(1, Id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Stop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
