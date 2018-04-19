package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.OrderEntity;
import ru.vsu.entity.VendorOrderEntity;
import ru.vsu.services.AbstractEntityService;

import java.time.LocalDateTime;

@Service
public class VendorOrderService {
    private static final String FIND_DRIVER = "Поиск водителя";
    private static final String STILL_GOES_ON = "Не закончен";
    private static final long ORDER_TYPE_ID = 6;
    private static final long NO_DRIVER_ID = 0;

    private OrderService orderService;
    private AbstractEntityService<OrderEntity> abstractEntityService;

    public VendorOrderService(OrderService orderService, AbstractEntityService<OrderEntity> abstractEntityService) {
        this.orderService = orderService;
        this.abstractEntityService = abstractEntityService;
    }


    public OrderEntity processOrder(VendorOrderEntity obj) {
        OrderEntity newOrderFromVendor = new OrderEntity();
        newOrderFromVendor.setClientFirstName(obj.getClientFirstName());
        newOrderFromVendor.setClientLastName(obj.getClientLastName());
        newOrderFromVendor.setClientPhoneNumber(obj.getClientPhoneNumber());
        newOrderFromVendor.setAddress(obj.getAddress());
        newOrderFromVendor.setGeoData(obj.getGeoData());
        newOrderFromVendor.setDestinationGeoData(obj.getDestinationGeoData());
        newOrderFromVendor.setCreator(obj.getCreator());
        newOrderFromVendor.setTypeId(ORDER_TYPE_ID);
        newOrderFromVendor.setDriverId(NO_DRIVER_ID);
        newOrderFromVendor.setStatusOrder(FIND_DRIVER);
        newOrderFromVendor.setOrderStartTime(LocalDateTime.now().toString());
        newOrderFromVendor.setOrderEndTime(STILL_GOES_ON);
        newOrderFromVendor.setName(newOrderFromVendor.getOrderStartTime());
        newOrderFromVendor.setOrderCost("3000");//сюда метод считающий цену
        newOrderFromVendor.setId(abstractEntityService.insert(newOrderFromVendor));
        return newOrderFromVendor;
    }
}
