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
public class JsonService {
    private DriverService driverService;
    private ObjectService objectService;

    @Autowired
    public JsonService(DriverService driverService, ObjectService objectService) {
        this.driverService = driverService;
        this.objectService = objectService;
    }

    public ObjectNode createJson() {


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode mainNode = mapper.createObjectNode();
        ArrayNode mainArrayNode = mapper.createArrayNode();
        mainNode.put("type", "FeatureCollection");
        //driver: geoPosition, car: Model + Number, order: status
        int driverNumber = 0;
        String[] geo;
        CarEntity car;
        ObjectNode objectNode1;
        ArrayList<DriverEntity> driverEntityArrayList = (ArrayList<DriverEntity>) driverService.getAll();
        for (DriverEntity driver : driverEntityArrayList) {
            //TODO:а че с обработкой нулов
            geo = driver.getDriverGeoData().split(",");//кладем координаты в массив
            car = (CarEntity) objectService.findById(driver.getCarId(), CarEntity.class);
            objectNode1 = mapper.createObjectNode();
            objectNode1.put("type", "Feature");
            objectNode1.put("id", driverNumber);
            objectNode1.set("geometry", mapper.createObjectNode()
                    .put("type", "Point")
                    .set("coordinates", mapper.createArrayNode()
                            .add(Double.parseDouble(geo[0]))
                            .add(Double.parseDouble(geo[1]))
                    ));
            objectNode1.set("properties", mapper.createObjectNode()
                    .put("balloonContent", car.getModel() + " " + car.getNumber())
                    .put("hintContent", car.getNumber()));
            objectNode1.set("options", mapper.createObjectNode()
                    .put("preset", "islands#blueAutoCircleIcon"));
            mainArrayNode.add(objectNode1);
            driverNumber++;
        }
/*
        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("type", "Feature");
        objectNode1.set("geometry", mapper.createObjectNode()
                .put("type", "Point")
                .set("coordinates", mapper.createArrayNode().add(55.831903).add(37.411961)));
        objectNode1.set("properties", mapper.createObjectNode()
                .put("balloonContent", "Auto x111xx36")
                .put("hintContent", "x111xx36"));

        mainArrayNode.add(objectNode1);
*/

        mainNode.set("features", mainArrayNode);
        return mainNode;
    }
}
