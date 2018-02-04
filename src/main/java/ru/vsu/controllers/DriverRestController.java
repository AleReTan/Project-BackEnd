package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/drivers", method = RequestMethod.POST)
    public void createOrder(@RequestBody DriverEntity d) {
        driverService.insert(d);
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.PATCH)
    public void updateOrder(@RequestBody DriverEntity d) {
        driverService.update(d);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@RequestBody DriverEntity d) {
        driverService.delete(d);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public DriverEntity getOrder(@PathVariable("id") long id) {
        return driverService.getObjectById(id);
    }
}
