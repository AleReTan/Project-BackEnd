package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.serviceImpl.DriverService;
import ru.vsu.services.serviceImpl.OrderService;

import java.util.List;

@RestController
public class DriverRestController {
    private DriverService driverService;
    private OrderService orderService;

    @Autowired
    public DriverRestController(DriverService driverService, OrderService orderService) {
        this.driverService = driverService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public List<DriverEntity> getDrivers(@RequestHeader("Authorization") String a) {
        return driverService.getAll();
    }

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/drivers", method = RequestMethod.POST)
    public ResponseEntity createDriver(@RequestBody DriverEntity d, @RequestHeader("Authorization") String a) {
        try {
            driverService.insert(d);
        } catch (IllegalArgumentException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error message", e.getMessage());
            return new ResponseEntity(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.PATCH)
    public void updateDriver(@RequestBody DriverEntity d, @RequestHeader("Authorization") String a) {
        driverService.update(d);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    public void deleteDriver(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        driverService.delete(id);
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.GET)
    public DriverEntity getDriver(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        return driverService.getObjectById(id);
    }

    @RequestMapping(value = "/drivers/available", method = RequestMethod.GET)
    public List<DriverEntity> getAvailableDrivers(@RequestHeader("Authorization") String a) {
        return driverService.getAllAvailableDriversOnShiftAndCars();
    }

    @RequestMapping(value = "/drivers/changeOnShift", method = RequestMethod.PATCH)
    public void changeOnShift(@RequestBody long id, @RequestHeader("Authorization") String a) {
        driverService.changeOnShift(id);
    }

    @RequestMapping(value = "/drivers/{id}/getOrder", method = RequestMethod.GET)
    public OrderEntity getOrderEntityByDriverId(@PathVariable long id, @RequestHeader("Authorization") String a) {

        OrderEntity orderEntity = orderService.getOrderEntityByDriverId(id);
        String print = (orderEntity != null) ? orderEntity.toString() : "null";
        System.out.println(print);
        return orderEntity;
    }

    @RequestMapping(value = "/drivers/{id}/IsOnShift", method = RequestMethod.GET)
    public String getDriverIsOnShift(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        return String.valueOf(driverService.isDriverOnShift(id));
    }

    @RequestMapping(value = "/drivers/{id}/changeGeoLocation", method = RequestMethod.PATCH)
    public void changeGeoLocation(@PathVariable("id") long id, @RequestBody String geoData, @RequestHeader("Authorization") String a) {
        driverService.changeGeoLocation(id, geoData);
    }

}
