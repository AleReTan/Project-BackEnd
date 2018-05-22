package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.CustomerOrderEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.AbstractEntityService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CustomerOrderService extends AbstractEntityService<OrderEntity> {
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
    private DriverService driverService;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Autowired
    public CustomerOrderService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, AttributeService attributeService, DriverService driverService) {
        super(objectService, paramsService, referenceService, attributeService);
        this.driverService = driverService;
    }

    public OrderEntity processOrder(CustomerOrderEntity obj) {
        OrderEntity newOrderFromCustomer = new OrderEntity();
        newOrderFromCustomer.setClientFirstName(obj.getClientFirstName());
        newOrderFromCustomer.setClientLastName(obj.getClientLastName());
        newOrderFromCustomer.setClientPhoneNumber(obj.getClientPhoneNumber());
        newOrderFromCustomer.setAddress(obj.getAddress());
        newOrderFromCustomer.setGeoData(obj.getGeoData());
        newOrderFromCustomer.setDestinationGeoData(obj.getDestinationGeoData());
        newOrderFromCustomer.setCreator(obj.getCreator());
        newOrderFromCustomer.setTypeId(ORDER_TYPE_ID);
        newOrderFromCustomer.setDriverId(driverService.getClosestDriverId(obj.getGeoData()));
        if (newOrderFromCustomer.getDriverId() == 0) newOrderFromCustomer.setStatusOrder(FIND_DRIVER);
        else newOrderFromCustomer.setStatusOrder(GOES_TO_CLIENT);
        newOrderFromCustomer.setOrderStartTime(LocalDateTime.now(ZoneId.of("Europe/Moscow")).format(formatter));
        newOrderFromCustomer.setOrderEndTime(STILL_GOES_ON);
        newOrderFromCustomer.setName("Заказ от " + newOrderFromCustomer.getOrderStartTime());
        newOrderFromCustomer.setOrderCost("3000");//сюда метод считающий цену
        newOrderFromCustomer.setId(insert(newOrderFromCustomer));
        newOrderFromCustomer.setStatusOrder(CREATED);
        newOrderFromCustomer.setOrderEndTime(STILL_IN_PROGRESS);
        return newOrderFromCustomer;
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

    public OrderEntity getCustomerOrderById(long id) {
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