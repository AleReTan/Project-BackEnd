package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.serviceImpl.OrderService;

import java.util.List;

@RestController
public class OrderRestController {
    private OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderEntity> getOrders(@RequestHeader("Authorization") String a) {
        return orderService.getAll();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void createOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.insert(o);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PATCH)
    public void updateOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.update(o);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        orderService.delete(id);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public OrderEntity getOrderById(@PathVariable("id") long id, @RequestHeader("Authorization") String a) {
        return orderService.getObjectById(id);
    }
}
