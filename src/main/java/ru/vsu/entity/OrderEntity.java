package ru.vsu.entity;


import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

import java.util.Objects;

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

    public OrderEntity(long id, String name, long typeId, String clientFirstName, String clientLastName, String clientPhoneNumber, String address, long driverId, String orderCost, String geoData, String orderStartTime, String orderEndTime, String statusOrder) {
        super(id, name, typeId);
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.address = address;
        this.driverId = driverId;
        this.orderCost = orderCost;
        this.geoData = geoData;
        this.orderStartTime = orderStartTime;
        this.orderEndTime = orderEndTime;
        this.statusOrder = statusOrder;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public String getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
    }

    public String getGeoData() {
        return geoData;
    }

    public void setGeoData(String geoData) {
        this.geoData = geoData;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(String orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return getDriverId() == that.getDriverId() &&
                Objects.equals(getClientFirstName(), that.getClientFirstName()) &&
                Objects.equals(getClientLastName(), that.getClientLastName()) &&
                Objects.equals(getClientPhoneNumber(), that.getClientPhoneNumber()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getOrderCost(), that.getOrderCost()) &&
                Objects.equals(getGeoData(), that.getGeoData()) &&
                Objects.equals(getOrderStartTime(), that.getOrderStartTime()) &&
                Objects.equals(getOrderEndTime(), that.getOrderEndTime()) &&
                Objects.equals(getStatusOrder(), that.getStatusOrder());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getClientFirstName(), getClientLastName(), getClientPhoneNumber(), getAddress(), getDriverId(), getOrderCost(), getGeoData(), getOrderStartTime(), getOrderEndTime(), getStatusOrder());
    }

    @Override
    public String toString() {
        return OrderEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", driverId=" + driverId +
                ", orderCost='" + orderCost + '\'' +
                ", geoData='" + geoData + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", statusOrder='" + statusOrder + '\'';
    }
}
