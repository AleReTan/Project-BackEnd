package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.CarEntity;
import ru.vsu.services.serviceImpl.CarService;

import java.util.List;

@RestController
public class CarRestController {

    private CarService carService;

    @Autowired
    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<CarEntity> getCars(@RequestHeader("Authorization") String a) {
        return carService.getAll();
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public void createCar(@RequestBody CarEntity c, @RequestHeader("Authorization") String a) {
        c.setTypeId(7);
        carService.insert(c);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.PATCH)
    public void updateCar(@RequestBody CarEntity c, @RequestHeader("Authorization") String a) {
        carService.update(c);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        carService.delete(id);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public CarEntity getCar(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        return carService.getObjectById(id);
    }

    @RequestMapping(value = "/cars/available", method = RequestMethod.GET)
    public List<CarEntity> getAvailableCars(@RequestHeader("Authorization") String a) {
        return carService.getAllAvailableCars();
    }
}
