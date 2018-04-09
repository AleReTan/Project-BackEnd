package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vsu.services.MyService;
import ru.vsu.services.serviceImpl.DriverService;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ObjectTypeController {
    private MyService objectTypeService;
    private DriverService driverService;

    @Autowired
    public ObjectTypeController(MyService objectTypeService, DriverService driverService) {
        this.objectTypeService = objectTypeService;
        this.driverService = driverService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String pushObjectTypes(Model model) {
        model.addAttribute("objectTypes", objectTypeService.getAll());
        return "view";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Boolean> test(@RequestHeader("Authorization") String a, @PathVariable("id") long id) {
        boolean answer = driverService.isDriverOnShift(id);
        if (answer) return Collections.singletonMap("true", true);
        else return Collections.singletonMap("false", false);
    }
}
