package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.OrderEntity;
import ru.vsu.entity.VendorOrderEntity;
import ru.vsu.services.serviceImpl.OrderService;
import ru.vsu.services.serviceImpl.VendorOrderService;

@RestController
public class VendorOrderRestController {
    private VendorOrderService vendorOrderService;
    private OrderService orderService;

    @Autowired
    public VendorOrderRestController(VendorOrderService vendorOrderService, OrderService orderService) {
        this.vendorOrderService = vendorOrderService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "/vendor/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderEntity> createOrder(@RequestBody VendorOrderEntity vendorOrder, @RequestHeader("Authorization") String a) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vendorOrder.setCreator(user.getUsername());
        try {
            return new ResponseEntity<>(vendorOrderService.processOrder(vendorOrder), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error message", e.getMessage());
            return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/vendor/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> deleteOrder(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        OrderEntity entity = orderService.getObjectById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (entity.getCreator().equals(user.getUsername())) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Error message", "This order doesn't belong to you");
        return new ResponseEntity<OrderEntity>(httpHeaders, HttpStatus.FORBIDDEN);
    }
}
