package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.VendorOrderEntity;
import ru.vsu.services.serviceImpl.VendorOrderService;

@RestController
public class VendorOrderRestController {
    private VendorOrderService vendorOrderService;

    @Autowired
    public VendorOrderRestController(VendorOrderService vendorOrderService) {
        this.vendorOrderService = vendorOrderService;
    }

    @RequestMapping(value = "/vendor/orders/create", method = RequestMethod.POST)
    public void createOrder(@RequestBody VendorOrderEntity vendorOrder, @RequestHeader("Authorization") String a) {
        vendorOrderService.processOrder(vendorOrder);
    }
}
