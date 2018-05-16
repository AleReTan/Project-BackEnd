package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.CustomerOrderEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.serviceImpl.CustomerOrderService;

@RestController
public class CustomerOrderRestController {
    private CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderRestController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @RequestMapping(value = "/customer/orders", method = RequestMethod.POST)
    public ResponseEntity<OrderEntity> createOrder(@RequestBody CustomerOrderEntity customerOrder, @RequestHeader("Authorization") String a) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customerOrder.setCreator(user.getUsername());
        try {
            return new ResponseEntity<>(customerOrderService.processOrder(customerOrder), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error message", e.getMessage());
            return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/customer/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderEntity> returnOrder(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        OrderEntity entity = customerOrderService.getCustomerOrderById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (entity.getCreator().equals(user.getUsername())) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Error message", "This order doesn't belong to you");
        return new ResponseEntity<OrderEntity>(httpHeaders, HttpStatus.FORBIDDEN);
    }
}
