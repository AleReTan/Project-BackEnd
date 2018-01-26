package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.CarEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.MyAbstractEntityService;

@Service
public class CarService extends MyAbstractEntityService<CarEntity> {
    public CarService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
    }
}