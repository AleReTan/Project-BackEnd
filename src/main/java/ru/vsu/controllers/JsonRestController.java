package ru.vsu.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.services.serviceImpl.JsonService;

@RestController
public class JsonRestController {
    private JsonService jsonService;

    @Autowired
    public JsonRestController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public ObjectNode getJson(@RequestHeader("Authorization") String a) {
        return jsonService.createJson();
    }

}
