package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.MyService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements MyService<OrderEntity> {
    private ObjectService<ObjectEntity> objectService;//или напрямую к дао?(что скорее всего не так)
    private ParamsService paramsService;
    private ReferenceService referenceService;

    @Autowired
    public OrderService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        this.objectService = objectService;
        this.paramsService = paramsService;
        this.referenceService = referenceService;
    }

    @Override
    public void delete(OrderEntity obj) {
        objectService.delete(obj);
        paramsService.deleteByObjectId(obj.getId());
        referenceService.deleteByObjectId(obj.getId());
    }

    @Override
    public void insert(OrderEntity obj) {
        objectService.insert(obj);
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ParamAttributeId.class)) {
                try {
                    field.setAccessible(true);
                    paramsService.insert(field.getAnnotation(ParamAttributeId.class).id(), obj.getId(), (String) field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Reference.class)) {
                try {
                    field.setAccessible(true);
                    referenceService.insert(field.getLong(obj), obj.getId(), field.getAnnotation(Reference.class).attrId());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(OrderEntity obj) {

    }

    @Override
    public List<OrderEntity> getAll() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        for (long id :
                objectService.getListOfObjectIdByObjectTypeId(OrderEntity.class.getAnnotation(ObjectTypeId.class).id())) {
            orderEntities.add((OrderEntity) objectService.findById(id, OrderEntity.class));
        }
        return orderEntities;
    }
}
