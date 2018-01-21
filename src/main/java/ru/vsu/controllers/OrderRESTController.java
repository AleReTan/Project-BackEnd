package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.entity.CarEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.facade.ServiceFacade;

import java.util.Arrays;
import java.util.List;

@RestController
public class OrderRESTController {
    private ServiceFacade serviceFacade;


    @Autowired
    public OrderRESTController(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }


    @RequestMapping(value = "/ot",method = RequestMethod.GET)
    public List getOT(){
        System.out.println(serviceFacade.getObjectService().findById(1, OrderEntity.class).toString());
        System.out.println(serviceFacade.getObjectService().findById(2, CarEntity.class).toString());
        System.out.println(Arrays.toString(serviceFacade.getObjectService().getListOfObjectIdByObjectTypeId(6).toArray()));
        //serviceFacade.getOrderService().insert((OrderEntity) serviceFacade.getObjectService().findById(1, OrderEntity.class));
        return serviceFacade.getObjectTypeService().getAll();
    }


    @RequestMapping(value = "ot/c", method = RequestMethod.POST)
    public OrderEntity setOT(@RequestBody OrderEntity o) {
        System.out.println(o.toString());
        return o;
    }
}
