package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.services.MyService;

import java.util.List;

@RestController
public class ObjectTypeRESTController {
    private MyService objectTypeService;

    @Autowired
    public ObjectTypeRESTController(MyService objectTypeService){
        this.objectTypeService = objectTypeService;
    }


    @RequestMapping(value = "/ot",method = RequestMethod.GET)
    public List getOT(){
        return objectTypeService.getAll();
    }

    @RequestMapping(value = "ot/c", method = RequestMethod.PUT)
    public void setOT(@RequestBody String ot){
        System.out.println(ot);
    }
}
