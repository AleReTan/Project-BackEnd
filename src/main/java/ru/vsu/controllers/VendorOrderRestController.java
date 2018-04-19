package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    @RequestMapping(value = "/vendor/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody VendorOrderEntity vendorOrder, @RequestHeader("Authorization") String a) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vendorOrder.setCreator(user.getUsername());
        return new ResponseEntity<>(vendorOrderService.processOrder(vendorOrder), HttpStatus.OK);
    }
}
