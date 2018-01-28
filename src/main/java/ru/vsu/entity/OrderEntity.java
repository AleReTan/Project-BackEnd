package ru.vsu.entity;


import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

@ObjectTypeId(id = 6)
public class OrderEntity extends ObjectEntity {

    @ParamAttributeId(id = 19)
    private String clientFirstName;

    @ParamAttributeId(id = 20)
    private String clientLastName;

    @ParamAttributeId(id = 21)
    private String clientPhoneNumber;

    @ParamAttributeId(id = 3)
    private String address;

    @Reference(attrId = 18)
    private long driverId;

    @ParamAttributeId(id = 8)
    private String orderCost;

    @ParamAttributeId(id = 22)
    private String geoData;

    @ParamAttributeId(id = 9)
    private String orderStartTime;

    @ParamAttributeId(id = 10)
    private String orderEndTime;

    @ParamAttributeId(id = 11)
    private String statusOrder;

    public OrderEntity() {
    }

}
