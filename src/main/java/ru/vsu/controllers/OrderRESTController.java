package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.dao.daoImpl.UtilDao;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.MyService;

import java.math.BigInteger;
import java.util.List;

@RestController
public class OrderRESTController {
    private MyService objectTypeService;

    @Autowired
    public OrderRESTController(MyService objectTypeService) {
        this.objectTypeService = objectTypeService;
    }


    @RequestMapping(value = "/ot",method = RequestMethod.GET)
    public List getOT(){
        UtilDao<OrderEntity> orderEntityUtilDao = new UtilDao<>();
        orderEntityUtilDao.findById(new BigInteger("1"), OrderEntity.class);
        return objectTypeService.getAll();
    }


    @RequestMapping(value = "ot/c", method = RequestMethod.POST)
    public OrderEntity setOT(@RequestBody OrderEntity o) {
        System.out.println(o.toString());
        return o;
    }
}
