package ru.vsu.services.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.CarEntity;
import ru.vsu.entity.DriverEntity;

import java.util.ArrayList;

@Service
public class YandexJsonService {
    private DriverService driverService;
    private ObjectService objectService;
    private ReferenceService referenceService;

    //айди атрибута driver из order, который показывает какой водитель на заказе(в условиях нашей бд это атрибут с id = 18)
    private static final int ON_ORDER_ATTRIBUTE = 18;

    @Autowired
    public YandexJsonService(DriverService driverService, ObjectService objectService, ReferenceService referenceService) {
        this.driverService = driverService;
        this.objectService = objectService;
        this.referenceService = referenceService;
    }

    public ObjectNode createJsonWithAllDrivers() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode mainNode = mapper.createObjectNode();
        ArrayNode mainArrayNode = mapper.createArrayNode();
        mainNode.put("type", "FeatureCollection");
        //driver: geoPosition, car: Model + Number, order: status
        String[] geo;
        CarEntity car;
        ObjectNode objectNode1;
        ArrayList<DriverEntity> driverEntityArrayList = (ArrayList<DriverEntity>) driverService.getAll();//здесь можем получать разных водителей
        for (DriverEntity driver : driverEntityArrayList) {
            //TODO:а че с обработкой нулов
            geo = driver.getDriverGeoData().split(",");//кладем координаты в массив
            car = (CarEntity) objectService.findById(driver.getCarId(), CarEntity.class);
            objectNode1 = mapper.createObjectNode();
            objectNode1.put("type", "Feature");
            objectNode1.set("geometry", mapper.createObjectNode()
                    .put("type", "Point")
                    .set("coordinates", mapper.createArrayNode()
                            .add(Double.parseDouble(geo[0]))
                            .add(Double.parseDouble(geo[1]))
                    ));
            objectNode1.set("properties", mapper.createObjectNode()
                    .put("balloonContent", car.getModel() + " " + car.getNumber())
                    .put("hintContent", car.getNumber())
                    .put("driverId", driver.getId()));
            //тип иконки, в зависимости, на заказе водитель или нет, синий-свободен, красный-на заказе
            String driverIconType;
            if (referenceService.isReferenceExistByRefIdAndAttrId(driver.getId(), ON_ORDER_ATTRIBUTE)) {
                driverIconType = "islands#redAutoCircleIcon";
            } else {
                driverIconType = "islands#blueAutoCircleIcon";
            }
            objectNode1.set("options", mapper.createObjectNode()
                    .put("preset", driverIconType));

            mainArrayNode.add(objectNode1);
        }

        mainNode.set("features", mainArrayNode);
        return mainNode;
    }

    public ObjectNode createJsonWithAvailableDrivers() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode mainNode = mapper.createObjectNode();
        ArrayNode mainArrayNode = mapper.createArrayNode();
        mainNode.put("type", "FeatureCollection");
        //driver: geoPosition, car: Model + Number, order: status
        String[] geo;
        CarEntity car;
        ObjectNode objectNode1;
        ArrayList<DriverEntity> driverEntityArrayList = (ArrayList<DriverEntity>) driverService.getAllAvailableDrivers();//здесь можем получать разных водителей
        for (DriverEntity driver : driverEntityArrayList) {
            //TODO:а че с обработкой нулов
            geo = driver.getDriverGeoData().split(",");//кладем координаты в массив
            car = (CarEntity) objectService.findById(driver.getCarId(), CarEntity.class);
            objectNode1 = mapper.createObjectNode();
            objectNode1.put("type", "Feature");
            objectNode1.set("geometry", mapper.createObjectNode()
                    .put("type", "Point")
                    .set("coordinates", mapper.createArrayNode()
                            .add(Double.parseDouble(geo[0]))
                            .add(Double.parseDouble(geo[1]))
                    ));
            objectNode1.set("properties", mapper.createObjectNode()
                    .put("balloonContent", car.getModel() + " " + car.getNumber())
                    .put("hintContent", car.getNumber())
                    .put("driverId", driver.getId()));
            //тип иконки, в зависимости, на заказе водитель или нет, синий-свободен, красный-на заказе
            String driverIconType = "islands#blueAutoCircleIcon";
            objectNode1.set("options", mapper.createObjectNode()
                    .put("preset", driverIconType));

            mainArrayNode.add(objectNode1);
        }
        mainNode.set("features", mainArrayNode);
        return mainNode;
    }

}
