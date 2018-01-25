package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.entity.OrderEntity;
import ru.vsu.facade.ServiceFacade;

import java.util.List;

@RestController
public class OrderRestController {
    private ServiceFacade serviceFacade;


    @Autowired
    public OrderRestController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }


    @RequestMapping(value = "/ot",method = RequestMethod.GET)
    public List test() {
        return serviceFacade.getCarService().getAll();
        //System.out.println(serviceFacade.getObjectService().findById(1, OrderEntity.class).toString());
        //System.out.println(serviceFacade.getObjectService().findById(2, CarEntity.class).toString());
        //System.out.println(Arrays.toString(serviceFacade.getObjectService().getListOfObjectIdByObjectTypeId(6).toArray()));
        //serviceFacade.getOrderService().insert((OrderEntity) serviceFacade.getObjectService().findById(1, OrderEntity.class));
        //return serviceFacade.getObjectTypeService().getAll();
    }

    @RequestMapping(value = "/orders/createOrder", method = RequestMethod.POST)
    public void createOrder(@RequestBody OrderEntity o) {
        serviceFacade.getOrderService().insert(o);
    }

    @RequestMapping(value = "/orders/updateOrder", method = RequestMethod.PATCH)
    public void updateOrder(@RequestBody OrderEntity o) {
        serviceFacade.getOrderService().update(o);
    }

    @RequestMapping(value = "/orders/deleteOrder", method = RequestMethod.DELETE)
    public void deleteOrder(@RequestBody OrderEntity o) {
        serviceFacade.getOrderService().delete(o);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<OrderEntity> getOrders() {
        return serviceFacade.getOrderService().getAll();
    }

    //TODO getOrderById не реализован
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public OrderEntity getOrder(long id) {
        return serviceFacade.getOrderService().getOrderById(id);
    }
}
