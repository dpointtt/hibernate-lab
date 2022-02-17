package ua.com.transitapp.repository;

import org.hibernate.SessionFactory;
import ua.com.transitapp.dao.RouteDao;
import ua.com.transitapp.entity.Route;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class RouteRepository implements RouteDao {

    private final SessionFactory sessionFactory;

    public RouteRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Route Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Route Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Route> findAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Route> routeList = entityManager.createQuery("select r from Route as r", Route.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return routeList;
    }

    @Override
    public Route findById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Route route = entityManager.find(Route.class, Id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return route;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Route").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteById(Long Id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Route as r where r.id = ?1")
                .setParameter(1, Id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(Route Item) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(Item);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Route> findRouteByTransport(String transport) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Route> routeList = entityManager.createQuery(
                "select r from Route r join r.transport t where t.transportType = ?1", Route.class)
                .setParameter(1, transport)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return routeList;
    }

    @Override
    public List<Route> findRouteByStop(String fromStop, String toStop) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<String> stops = Arrays.asList(fromStop, toStop);
        List<Route> routeList = entityManager.createQuery(
                "select r from Route r " +
                "join r.routeStopList rs " +
                "join rs.stop s " +
                "join r.transport t " +
                "where s.stopName in (?1) " +
                "group by r.routeNumber " +
                "having count(r) > 1", Route.class)
                        .setParameter(1, stops)
                                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return routeList;
    }
}
