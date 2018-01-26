package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.MyAbstractEntityService;


@Service
public class OrderService extends MyAbstractEntityService<OrderEntity> {

    public OrderService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
    }
}
