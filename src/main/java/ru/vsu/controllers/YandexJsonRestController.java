package ru.vsu.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.services.serviceImpl.YandexJsonService;

@RestController
public class YandexJsonRestController {
    private YandexJsonService yandexJsonService;

    @Autowired
    public YandexJsonRestController(YandexJsonService yandexJsonService) {
        this.yandexJsonService = yandexJsonService;
    }

    @RequestMapping(value = "/allCarsJson", method = RequestMethod.GET)
    public ObjectNode getJson(@RequestHeader("Authorization") String a) {
        return yandexJsonService.createJson();
    }

}
