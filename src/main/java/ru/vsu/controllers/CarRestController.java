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
    public List<CarEntity> getCars() {
        return carService.getAll();
    }

    @RequestMapping(value = "/cars/addCar", method = RequestMethod.POST)
    public void createCar(@RequestBody CarEntity c) {
        carService.insert(c);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.PATCH)
    public void updateCar(@RequestBody CarEntity c) {
        carService.update(c);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
    public void deleteCar(@RequestBody CarEntity c) {
        carService.delete(c);
    }

    @RequestMapping(value = "/cars/{id}", method = RequestMethod.GET)
    public CarEntity getCar(@PathVariable("id") long id) {
        return carService.getObjectById(id);
    }
}
