package ru.vsu.entity;


import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

import java.util.Objects;

@ObjectTypeId(id = 6)
public class OrderEntity extends ObjectEntity {
    @Reference(attrId = 17)
    private long clientId;

    @ParamAttributeId(id = 3)
    private String address;

    @Reference(attrId = 16)
    private long carId;

    @ParamAttributeId(id = 8)
    private String orderCost;

    @ParamAttributeId(id = 9)
    private String orderStartTime;

    @ParamAttributeId(id = 10)
    private String orderEndTime;

    @ParamAttributeId(id = 11)
    private String statusOrder;

    public OrderEntity() {
    }

    public OrderEntity(long id, String name, long typeId, long clientId, String address, long carId, String orderCost, String orderStartTime, String orderEndTime, String statusOrder) {
        super(id, name, typeId);
        this.clientId = clientId;
        this.address = address;
        this.carId = carId;
        this.orderCost = orderCost;
        this.orderStartTime = orderStartTime;
        this.orderEndTime = orderEndTime;
        this.statusOrder = statusOrder;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
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
        return getClientId() == that.getClientId() &&
                getCarId() == that.getCarId() &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getOrderCost(), that.getOrderCost()) &&
                Objects.equals(getOrderStartTime(), that.getOrderStartTime()) &&
                Objects.equals(getOrderEndTime(), that.getOrderEndTime()) &&
                Objects.equals(getStatusOrder(), that.getStatusOrder());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getClientId(), getAddress(), getCarId(), getOrderCost(), getOrderStartTime(), getOrderEndTime(), getStatusOrder());
    }

    @Override
    public String toString() {
        return OrderEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", clientId='" + clientId + '\'' +
                ", address='" + address + '\'' +
                ", carId=" + carId +
                ", orderCost='" + orderCost + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", statusOrder='" + statusOrder + '\'';
    }
}
