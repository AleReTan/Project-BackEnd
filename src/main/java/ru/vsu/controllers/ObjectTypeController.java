package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.services.MyService;

@Controller
@RequestMapping("/")
public class ObjectTypeController {
    private MyService objectTypeService;

    @Autowired
    public ObjectTypeController(MyService objectTypeService){
        this.objectTypeService = objectTypeService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String pushObjectTypes(Model model) {
        model.addAttribute("objectTypes", objectTypeService.getAll());
        return "view";
    }//убрать модель, создать объект конвертящийся в json
}
