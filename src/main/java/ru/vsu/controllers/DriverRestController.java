package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.entity.DriverEntity;
import ru.vsu.services.serviceImpl.DriverService;

import java.util.List;

@RestController
public class DriverRestController {
    private DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public List<DriverEntity> getOrders() {
        return driverService.getAll();
    }

    @RequestMapping(value = "/drivers/createDriver", method = RequestMethod.POST)
    public void createOrder(@RequestBody DriverEntity o) {
        driverService.insert(o);
    }

    @RequestMapping(value = "/drivers/updateDriver", method = RequestMethod.PATCH)
    public void updateOrder(@RequestBody DriverEntity o) {
        driverService.update(o);
    }

    @RequestMapping(value = "/drivers/deleteDriver", method = RequestMethod.DELETE)
    public void deleteOrder(@RequestBody DriverEntity o) {
        driverService.delete(o);
    }

    //TODO getObjectById не реализован, пока не знаю как
    @RequestMapping(value = "/order?{{id}}", method = RequestMethod.GET)
    public DriverEntity getOrder(long id) {
        return driverService.getObjectById(id);
    }
}
