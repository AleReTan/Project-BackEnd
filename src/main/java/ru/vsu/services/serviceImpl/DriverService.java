package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.ObjectEntity;
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

    @Autowired
    public DriverService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, AttributeService attributeService) {
        super(objectService, paramsService, referenceService, attributeService);
    }

    @Override
    public long insert(DriverEntity obj) {
        obj.setTypeId(DRIVER_TYPE_ID);
        obj.setName(obj.getLastName() + " " + obj.getPhoneNumber());
        obj.setDriverGeoData(BASIC_GEO_DATA);
        obj.setOnShift(FALSE);
        return super.insert(obj);
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
        obj.setOnShift(Boolean.toString(!isDriverOnShift(obj.getId())));
        super.update(obj);
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
        //geoData.substring(1,geoData.length()-1)
        obj.setDriverGeoData(geoData.replace("\"", ""));
        super.update(obj);
    }

    public long getClosestDriverId(String geoPosition) {
        List<DriverEntity> allAvailableDrivers = getAllAvailableDriversOnShiftAndCars();
        double minDistance = Double.MAX_VALUE;
        double currentDistance;
        long closestDriverId = 0;
        ArrayList<Double> targetGeo = parseGeoData(geoPosition);
        ArrayList<Double> driverGeo;
        //если нет свободных, ставим 0, то есть поиск водителя
        if (allAvailableDrivers.isEmpty()) return 0;
        for (DriverEntity driver : allAvailableDrivers) {
            driverGeo = parseGeoData(driver.getDriverGeoData());
            currentDistance = Math.sqrt(
                    Math.pow(targetGeo.get(0) - driverGeo.get(0), 2) +
                            Math.pow(targetGeo.get(1) - driverGeo.get(1), 2));
            if (minDistance > currentDistance) {
                minDistance = currentDistance;
                closestDriverId = driver.getId();
            }
        }
        return closestDriverId;
    }

    private ArrayList<Double> parseGeoData(String geoData) {
        ArrayList<Double> arrayGeoData = new ArrayList<Double>();
        for (String geo : geoData.split(",")) {
            arrayGeoData.add(Double.parseDouble(geo));
        }
        return arrayGeoData;
    }

}
