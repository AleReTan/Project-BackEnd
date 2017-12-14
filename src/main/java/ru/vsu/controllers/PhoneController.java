package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.services.MyService;

@Controller
@RequestMapping("/")
public class PhoneController {

    private  MyService phoneService;

    @Autowired
    public PhoneController(MyService phoneService){
        this.phoneService = phoneService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String pushPhones(Model model) {
        model.addAttribute("phones", phoneService.getAll());
        return "view";
    }

}
