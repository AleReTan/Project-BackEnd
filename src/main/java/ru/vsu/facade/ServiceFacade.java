package ru.vsu.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.services.serviceImpl.*;

@Component
public class ServiceFacade {
    private ObjectTypeService objectTypeService;
    private ObjectService objectService;
    private AttributeService attributeService;
    private ParamsService paramsService;
    private ReferenceService referenceService;
    private OrderService orderService;
    private CarService carService;

    @Autowired
    public ServiceFacade(ObjectTypeService objectTypeService, ObjectService objectService, AttributeService attributeService, ParamsService paramsService, ReferenceService referenceService, OrderService orderService, CarService carService) {
        this.objectTypeService = objectTypeService;
        this.objectService = objectService;
        this.attributeService = attributeService;
        this.paramsService = paramsService;
        this.referenceService = referenceService;
        this.orderService = orderService;
        this.carService = carService;
    }

    public ObjectTypeService getObjectTypeService() {
        return objectTypeService;
    }

    public ObjectService getObjectService() {
        return objectService;
    }

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public ParamsService getParamsService() {
        return paramsService;
    }

    public ReferenceService getReferenceService() {
        return referenceService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public CarService getCarService() {
        return carService;
    }
}
