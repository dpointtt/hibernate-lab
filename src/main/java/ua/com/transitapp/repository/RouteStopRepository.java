package ua.com.transitapp.repository;

import org.hibernate.SessionFactory;
import ua.com.transitapp.dao.RouteStopDao;
import ua.com.transitapp.entity.RouteStop;

import javax.persistence.EntityManager;
import java.util.List;

public class RouteStopRepository implements RouteStopDao {

    private final SessionFactory sessionFactory;

    public RouteStopRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(RouteStop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(RouteStop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<RouteStop> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<RouteStop> routeStopList = entityManager.createQuery("select rs from RouteStop as rs", RouteStop.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return routeStopList;
    }

    @Override
    public RouteStop findById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        RouteStop routeStop = entityManager.find(RouteStop.class, Id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return routeStop;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from RouteStop").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from RouteStop as rs where rs.id = ?1")
                .setParameter(1, Id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(RouteStop Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
