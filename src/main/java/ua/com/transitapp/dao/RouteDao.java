package ua.com.transitapp.dao;

import ua.com.transitapp.entity.Route;

import java.util.List;

public interface RouteDao extends GeneralDao<Route>{

    List<Route> findRouteByTransport(String transport);
    List<Route> findRouteByStop(String fromStop, String toStop);

}
