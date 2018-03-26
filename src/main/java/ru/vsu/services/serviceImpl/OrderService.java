package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.AbstractEntityService;

import java.time.LocalDateTime;

@Service
public class OrderService extends AbstractEntityService<OrderEntity> {

    public OrderService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
    }

    @Override
    public void insert(OrderEntity obj) {
        //если водитель не 0, значит назначен, ставим статус - водитель движется, если 0, то поиск
        if (obj.getDriverId() != 0)
            obj.setStatusOrder("Водитель движется к клиенту");
        else obj.setStatusOrder("Поиск водителя");
        obj.setOrderStartTime(LocalDateTime.now().toString());
        obj.setOrderEndTime("Не закончен");
        super.insert(obj);
    }

    @Override
    public void update(OrderEntity obj) {
        if (obj.getStatusOrder().equals("Поиск водителя") && obj.getDriverId() != 0) {
            obj.setStatusOrder("Водитель движется к клиенту");
        }
        if (obj.getStatusOrder().equals("Заказ завершен") ||
                obj.getStatusOrder().equals("Заказ отменен")) {
            obj.setOrderEndTime(LocalDateTime.now().toString());
            obj.setDriverId(0);//0 для автоматического удаления этого референса планировшиком
        }
        super.update(obj);
    }
}
