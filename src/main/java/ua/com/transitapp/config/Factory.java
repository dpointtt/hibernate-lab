package ua.com.transitapp.config;

import org.hibernate.SessionFactory;
import ua.com.transitapp.dao.RouteDao;
import ua.com.transitapp.dao.RouteStopDao;
import ua.com.transitapp.dao.StopDao;
import ua.com.transitapp.dao.TransportDao;
import ua.com.transitapp.repository.RouteRepository;
import ua.com.transitapp.repository.RouteStopRepository;
import ua.com.transitapp.repository.StopRepository;
import ua.com.transitapp.repository.TransportRepository;

import javax.persistence.Persistence;

public class Factory {

    public final static Factory INSTANCE = new Factory();

    public static Factory getInstance(){
        return INSTANCE;
    }

    private final SessionFactory session;

    public Factory(){
        this.session = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.lab");
    }

    public RouteDao getRouteDao(){
        return new RouteRepository(session);
    }

    public TransportDao getTransportDao(){
        return new TransportRepository(session);
    }

    public StopDao getStopDao(){
        return new StopRepository(session);
    }

    public RouteStopDao getRouteStopDao(){
        return new RouteStopRepository(session);
    }

}
