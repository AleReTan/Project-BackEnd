package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.entity.VendorOrderEntity;
import ru.vsu.services.AbstractEntityService;

import java.time.LocalDateTime;

@Service
public class VendorOrderService extends AbstractEntityService<OrderEntity> {
    private static final String FIND_DRIVER = "Поиск водителя";
    private static final String GOES_TO_CLIENT = "Водитель движется к клиенту";
    private static final String PICKED_CLIENT = "Водитель с клиентом";
    private static final String ORDER_COMPLETE = "Заказ завершен";
    private static final String ORDER_CANCELED = "Заказ отменен";
    private static final String STILL_GOES_ON = "Не закончен";
    private static final String STILL_IN_PROGRESS = "Still in progress";
    private static final String CREATED = "Created";
    private static final String IN_PROGRESS = "In progress";
    private static final String COMPLETED = "Completed";
    private static final String CANCELED = "Canceled";
    private static final long ORDER_TYPE_ID = 6;
    private static final long NO_DRIVER_ID = 0;

    public VendorOrderService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, AttributeService attributeService) {
        super(objectService, paramsService, referenceService, attributeService);
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
        newOrderFromVendor.setId(insert(newOrderFromVendor));
        newOrderFromVendor.setStatusOrder(CREATED);
        newOrderFromVendor.setOrderEndTime(STILL_IN_PROGRESS);
        return newOrderFromVendor;
    }

    public OrderEntity updateOrderStatus(OrderEntity obj, String status) {
        switch (status) {
            case PICKED_CLIENT:
                obj.setStatusOrder(IN_PROGRESS);
                obj.setOrderEndTime(STILL_IN_PROGRESS);
                break;
            case ORDER_COMPLETE:
                obj.setStatusOrder(COMPLETED);
                break;
            default:
                break;
        }
        return obj;
    }

    public OrderEntity getVendorOrderById(long id) {
        OrderEntity orderEntity = getObjectById(id);
        if (STILL_GOES_ON.equals(orderEntity.getOrderEndTime()))
            orderEntity.setOrderEndTime(STILL_IN_PROGRESS);
        switch (orderEntity.getStatusOrder()) {
            case FIND_DRIVER:
                orderEntity.setStatusOrder(CREATED);
                break;
            case GOES_TO_CLIENT:
                orderEntity.setStatusOrder(IN_PROGRESS);
                break;
            case PICKED_CLIENT:
                orderEntity.setStatusOrder(IN_PROGRESS);
                break;
            case ORDER_COMPLETE:
                orderEntity.setStatusOrder(COMPLETED);
                break;
            case ORDER_CANCELED:
                orderEntity.setStatusOrder(CANCELED);
                break;
            default:
                break;
        }
        return orderEntity;
    }
}