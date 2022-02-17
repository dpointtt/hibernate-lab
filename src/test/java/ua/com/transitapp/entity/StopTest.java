package ua.com.transitapp.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.com.transitapp.config.Factory;
import ua.com.transitapp.dao.StopDao;

import java.util.List;

public class StopTest {

    private final Factory instance = Factory.getInstance();

    @Test
    public void Test(){
        StopDao stopRepository = instance.getStopDao();

        // save
        Stop stop = new Stop();
        stop.setStopName("вулиця Шевченка");
        stopRepository.save(stop);
        Assert.assertEquals(1, stopRepository.findAll().size());

        // update
        stop.setStopName("вулиця Івана-Франка");
        stopRepository.update(stop);

        // find all
        List<Stop> stopList = stopRepository.findAll();
        Assert.assertEquals("вулиця Івана-Франка", stopList.get(0).getStopName());

        // find by id
        Assert.assertEquals("вулиця Івана-Франка", stopRepository.findById(1L).getStopName());

        // delete by id
        stopRepository.deleteById(1L);
        Assert.assertEquals(0, stopRepository.findAll().size());
    }

}
