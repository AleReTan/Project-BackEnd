package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.entity.VendorEntity;
import ru.vsu.services.AbstractEntityService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class OrderService extends AbstractEntityService<OrderEntity> {
    private static final String DRIVER_TO_CLIENT = "Водитель движется к клиенту";
    private static final String FIND_DRIVER = "Поиск водителя";
    private static final String STILL_GOES_ON = "Не закончен";
    private static final String ORDER_COMPLETE = "Заказ завершен";
    private static final String ORDER_CANCELED = "Заказ отменен";
    private static final String PICKED_CLIENT = "Водитель с клиентом";
    private static final long ORDER_TYPE_ID = 6;
    private VendorService vendorService;
    private VendorOrderService vendorOrderService;

    @Autowired
    public OrderService(ObjectService<ObjectEntity> objectService,
                        ParamsService paramsService,
                        ReferenceService referenceService,
                        AttributeService attributeService,
                        VendorService vendorService,
                        VendorOrderService vendorOrderService) {
        super(objectService, paramsService, referenceService, attributeService);
        this.vendorService = vendorService;
        this.vendorOrderService = vendorOrderService;
    }

    @Override
    public long insert(OrderEntity obj) {
        //если водитель не 0, значит назначен, ставим статус - водитель движется, если 0, то поиск
        obj.setTypeId(ORDER_TYPE_ID);
        if (obj.getDriverId() != 0) {
            obj.setStatusOrder(DRIVER_TO_CLIENT);
        } else {
            obj.setStatusOrder(FIND_DRIVER);
        }
        obj.setOrderStartTime(LocalDateTime.now().toString());
        obj.setOrderEndTime(STILL_GOES_ON);
        obj.setName(obj.getOrderStartTime());
        return super.insert(obj);
    }

    @Override
    public void update(OrderEntity obj) {
        if (obj.getStatusOrder().equals(FIND_DRIVER) && obj.getDriverId() != 0) {
            obj.setStatusOrder(DRIVER_TO_CLIENT);
        }
        super.update(obj);
    }

    /**
     * используется для установки статуса "забрал коиента"
     *
     * @param obj
     */
    public void pickClient(OrderEntity obj) {
        obj.setStatusOrder(PICKED_CLIENT);
        super.update(obj);
        sendUpdatedOrderToVendor(obj);
    }

    /**
     * используется для отмены заказа: установка времени, статуса и ссылки на водителя
     *
     * @param obj
     */
    public void cancelOrder(OrderEntity obj) {
        obj.setStatusOrder(ORDER_CANCELED);
        obj.setOrderEndTime(LocalDateTime.now().toString());
        obj.setDriverId(0);
        super.update(obj);
    }

    /**
     * используется для закрытия заказа: установка времени, статуса и ссылки на водителя
     *
     * @param obj
     */
    public void closeOrder(OrderEntity obj) {
        obj.setStatusOrder(ORDER_COMPLETE);
        obj.setOrderEndTime(LocalDateTime.now().toString());
        obj.setDriverId(0);
        super.update(obj);
        sendUpdatedOrderToVendor(obj);
    }

    /**
     * @return возвращает list заказов которые не завершены или не отменены
     */
    public List<OrderEntity> getAllActiveOrders() {
        List<OrderEntity> listOfActiveOrders = new ArrayList<>();
        for (OrderEntity orderEntity : getAll()) {
            if (!(orderEntity.getStatusOrder().equals(ORDER_COMPLETE) ||
                    orderEntity.getStatusOrder().equals(ORDER_CANCELED))) {
                listOfActiveOrders.add(orderEntity);
            }
        }
        return listOfActiveOrders;
    }

    /**
     * @return возвращает list заказов которые "поиск водителя"
     */
    public List<OrderEntity> getAllOrdersWithoutDriver() {
        List<OrderEntity> listOfOrdersWithoutDriver = new ArrayList<>();
        for (OrderEntity orderEntity : getAll()) {
            if (orderEntity.getStatusOrder().equals(FIND_DRIVER)) {
                listOfOrdersWithoutDriver.add(orderEntity);
            }
        }
        return listOfOrdersWithoutDriver;
    }

    /**
     * метод бросает вендору ордер с обновленным статусом
     *
     * @param obj
     */
    private void sendUpdatedOrderToVendor(OrderEntity obj) {
//        long vendorId = vendorService.getVendorIdByLogin(obj.getCreator());
//        VendorEntity vendor = vendorService.getObjectById(vendorId);
//        vendor.getVendorURL();
        for (VendorEntity vendor : vendorService.getAll()) {
            if (obj.getCreator().equals(vendor.getVendorLogin())) {
                String originalInput = vendor.getVendorAccessLogin() + ":" + vendor.getVendorAccessPassword();
                String token = "Base " + Base64.getEncoder().encodeToString(originalInput.getBytes());
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
                HttpEntity<OrderEntity> entity = new HttpEntity<>(vendorOrderService.updateOrderStatus(obj, obj.getStatusOrder()), httpHeaders);
                new RestTemplate().patchForObject(
                        vendor.getVendorURL(),
                        entity,
                        OrderEntity.class);
            }
        }
    }
}
