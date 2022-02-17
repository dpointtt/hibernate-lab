package ua.com.transitapp.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.com.transitapp.config.Factory;
import ua.com.transitapp.dao.RouteDao;

import java.sql.Time;
import java.util.List;

public class RouteTest {

    private final Factory instance = Factory.getInstance();

    @Test
    public void Test(){
        RouteDao routeRepository = instance.getRouteDao();

        // save
        Route route = new Route();
        route.setRouteNumber(411);
        route.setRouteStart("вулиця Шевченка");
        route.setRouteEnd("вулиця Івана-Франка");
        route.setTimeStart(new Time(6, 0, 0));
        route.setTimeEnd(new Time(23, 30, 0));
        route.setInterval_(7);
        routeRepository.save(route);
        Assert.assertEquals(1, routeRepository.findAll().size());

        // update
        route.setRouteNumber(412);
        routeRepository.update(route);

        // find all
        List<Route> routeList = routeRepository.findAll();
        Assert.assertEquals("вулиця Шевченка", routeList.get(0).getRouteStart());

        // find by id
        Assert.assertEquals("вулиця Івана-Франка", routeRepository.findById(1L).getRouteEnd());

        // delete all
        routeRepository.deleteAll();
        Assert.assertEquals(0, routeRepository.findAll().size());

    }

}
