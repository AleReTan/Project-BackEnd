package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.OrderEntity;
import ru.vsu.services.AbstractEntityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService extends AbstractEntityService<DriverEntity> {
    //айди атрибута driver из order, который показывает какой водитель на заказе(в условиях нашей бд это атрибут с id = 18)
    private static final long ON_ORDER_ATTRIBUTE = 18;
    private static final long CAR_ATTRIBUTE = 16;
    private static final long LOGIN_ATTRIBUTE = 26;
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String BASIC_GEO_DATA = "51.661035, 39.199017";//пл.Ленина
    private static final long DRIVER_TYPE_ID = 8;
    private OrderService orderService;

    @Autowired
    public DriverService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, OrderService orderService, AttributeService attributeService) {
        super(objectService, paramsService, referenceService, attributeService);
        this.orderService = orderService;
    }

    @Override
    public void insert(DriverEntity obj) {
        obj.setTypeId(DRIVER_TYPE_ID);
        obj.setName(obj.getLastName() + " " + obj.getPhoneNumber());
        obj.setDriverGeoData(BASIC_GEO_DATA);
        obj.setOnShift(FALSE);
        super.insert(obj);
    }

    public List<DriverEntity> getAllAvailableDrivers() {
        List<DriverEntity> listOfAvailableDrivers = new ArrayList<>();

        for (DriverEntity driverEntity : getAll()) {
            if (!referenceService.isReferenceExistByRefIdAndAttrId(driverEntity.getId(), ON_ORDER_ATTRIBUTE)) {
                listOfAvailableDrivers.add(driverEntity);
            }
        }
        return listOfAvailableDrivers;
    }

    /**
     * если водитель на машине и на смене, то добавим
     *
     * @return
     */
    public List<DriverEntity> getAllDriversOnShiftAndCars() {
        List<DriverEntity> listOfAvailableDrivers = new ArrayList<>();

        for (DriverEntity driverEntity : getAll()) {
            if (referenceService.isReferenceExistByObjectIdAndAttrId(driverEntity.getId(), CAR_ATTRIBUTE) &&
                    isDriverOnShift(driverEntity.getId()))
                listOfAvailableDrivers.add(driverEntity);
        }
        return listOfAvailableDrivers;
    }

    /**
     * если водитель на машине и на смене, а также не на заказе, то добавим
     *
     * @return
     */
    public List<DriverEntity> getAllAvailableDriversOnShiftAndCars() {
        List<DriverEntity> listOfAvailableDrivers = new ArrayList<>();
        for (DriverEntity driverEntity : getAll()) {
            if (referenceService.isReferenceExistByObjectIdAndAttrId(driverEntity.getId(), CAR_ATTRIBUTE) &&
                    isDriverOnShift(driverEntity.getId()))
                if (!referenceService.isReferenceExistByRefIdAndAttrId(driverEntity.getId(), ON_ORDER_ATTRIBUTE))
                    listOfAvailableDrivers.add(driverEntity);
        }
        return listOfAvailableDrivers;
    }

    public boolean isDriverOnShift(long id) {
        DriverEntity driverEntity = getObjectById(id);
        return TRUE.equals(driverEntity.getOnShift());
    }

    /**
     * меняет текущее значение атрибута "на смене" на противоположное
     *
     * @param id
     */
    public void changeOnShift(long id) {

        DriverEntity obj = getObjectById(id);
        System.out.println(Boolean.toString(!isDriverOnShift(obj.getId())));
        obj.setOnShift(Boolean.toString(!isDriverOnShift(obj.getId())));
        super.update(obj);
    }

    /**
     * возвращает заказ по водителю(если назначен), если не назначет вернет null
     *
     * @param id
     * @return
     */
    public OrderEntity getOrderEntityByDriverId(long id) {
        if (referenceService.isReferenceExistByRefIdAndAttrId(id, ON_ORDER_ATTRIBUTE)) {
            return orderService.getObjectById(referenceService.getObjectIdByRefIdAndAttrId(id, ON_ORDER_ATTRIBUTE));
        } else {
            return null;
        }
    }

    /**
     * возвращает айди водителя по связке айди атрибута "login" из таблицы attribute
     * и строке login, поскольку login - примари кей в  users, значение уникальное
     * и getObjectIdByAttributeIdAndValue вернет одну запись
     *
     * @param login
     * @return
     */
    public long getDriverIdByLogin(String login) {
        return paramsService.getObjectIdByAttributeIdAndValue(LOGIN_ATTRIBUTE, login);
    }

    public void changeGeoLocation(long id, String geoData) {
        DriverEntity obj = getObjectById(id);
        obj.setDriverGeoData(geoData);
        super.update(obj);
    }
}
