package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity createOrder(@RequestBody VendorOrderEntity vendorOrder, @RequestHeader("Authorization") String a) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vendorOrder.setCreator(user.getUsername());
        return new ResponseEntity<>(vendorOrderService.processOrder(vendorOrder), HttpStatus.OK);
    }

    //get ? pathVariable id return order
    //проверять юзера, и проверять, его ли это ордер
//    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
//    public OrderEntity deleteOrder(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
//        OrderEntity entity = orderService.getObjectById(id);
//        //if (ордер юзера)
//        return entity;
//    }
}
