package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @SuppressWarnings("Duplicates")
    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity createCar(@RequestBody CarEntity c, @RequestHeader("Authorization") String a) {
        try {
            carService.insert(c);
        } catch (IllegalArgumentException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error message", e.getMessage());
            return new ResponseEntity(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
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
