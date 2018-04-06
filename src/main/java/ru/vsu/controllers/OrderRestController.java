package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return orderService.getAll();//здесь меняем на getAllActiveOrders
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity createOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        //TODO в хедер, org.springframework.http.ResponseEntity#ResponseEntity(org.springframework.util.MultiValueMap<java.lang.String,java.lang.String>, org.springframework.http.HttpStatus)
        //вместо e.getMessage() в response делать хедер
        try {
            orderService.insert(o);
        } catch (IllegalArgumentException e) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Error message", e.getMessage());
            return new ResponseEntity(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.PATCH)
    public void updateOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.update(o);
    }

    @RequestMapping(value = "/orders/pickclient", method = RequestMethod.PATCH)
    public void pickClient(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.pickClient(o);
    }

    @RequestMapping(value = "/orders/closeorder", method = RequestMethod.PATCH)
    public void closeOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.closeOrder(o);
    }

    @RequestMapping(value = "/orders/cancelorder", method = RequestMethod.PATCH)
    public void cancelOrder(@RequestBody OrderEntity o, @RequestHeader("Authorization") String a) {
        orderService.cancelOrder(o);
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
