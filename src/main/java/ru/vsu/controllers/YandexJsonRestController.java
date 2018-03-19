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

    @RequestMapping(value = "/allDriversJson", method = RequestMethod.GET)
    public ObjectNode getJsonWithAllDrivers(@RequestHeader("Authorization") String a) {
        return yandexJsonService.createJsonWithAllDrivers();
    }

    @RequestMapping(value = "/availableDriversJson", method = RequestMethod.GET)
    public ObjectNode getJsonWithAvailableDrivers(@RequestHeader("Authorization") String a) {
        return yandexJsonService.createJsonWithAvailableDrivers();
    }
}
