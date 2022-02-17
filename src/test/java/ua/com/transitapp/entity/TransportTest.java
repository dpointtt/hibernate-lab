package ua.com.transitapp.entity;

import org.junit.Assert;
import org.junit.Test;
import ua.com.transitapp.config.Factory;
import ua.com.transitapp.dao.TransportDao;

import java.util.List;

public class TransportTest {

    private final Factory instance = Factory.getInstance();

    @Test
    public void Test(){
        TransportDao transportRepository = instance.getTransportDao();

        // save
        Transport transport = new Transport();
        transport.setTransportType("Автобус");
        transportRepository.save(transport);
        Assert.assertEquals(1, transportRepository.findAll().size());

        // update
        transport.setTransportType("Трамвай");
        transportRepository.update(transport);

        // find all
        List<Transport> transportList = transportRepository.findAll();
        Assert.assertEquals("Трамвай", transportList.get(0).getTransportType());

        // find by id
        Assert.assertEquals("Трамвай", transportRepository.findById(1L).getTransportType());

        // delete
        transportRepository.delete(transport);
        Assert.assertEquals(0, transportRepository.findAll().size());

    }

}
