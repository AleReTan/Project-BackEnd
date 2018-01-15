package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class OrderService implements MyService<OrderEntity> {

    @Override
    public void delete(OrderEntity obj) {

    }

    @Override
    public void insert(OrderEntity obj) {

    }

    @Override
    public void update(OrderEntity obj) {

    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}
