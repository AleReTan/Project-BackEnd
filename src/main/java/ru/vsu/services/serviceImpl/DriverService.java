package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.AbstractEntityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService extends AbstractEntityService<DriverEntity> {
    //айди атрибута driver из order, который показывает какой водитель на заказе(в условиях нашей бд это атрибут с id = 18)
    private static final int ON_ORDER_ATTRIBUTE = 18;
    private static final int CAR_ATTRIBUTE = 16;
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    public DriverService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
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
        return driverEntity.getOnShift().equals(TRUE);
    }

    /**
     * меняет текущее значение атрибута "на смене" на противоположное
     *
     * @param obj
     */
    public void changeOnShift(DriverEntity obj) {
        System.out.println(Boolean.toString(!isDriverOnShift(obj.getId())));
        obj.setOnShift(Boolean.toString(!isDriverOnShift(obj.getId())));
        super.update(obj);
    }
}
