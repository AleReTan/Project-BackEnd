package ru.vsu.entity;


import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

import java.util.Objects;

@ObjectTypeId(id = 6)
public class OrderEntity extends ObjectEntity {
    //todo: add customer reference, delete first,las name's
    @ParamAttributeId(id = 1)
    private String firstName;

    @ParamAttributeId(id = 2)
    private String lastName;

    @ParamAttributeId(id = 3)
    private String address;

    @ParamAttributeId(id = 4)
    private String mobPhone;

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

    public OrderEntity(long id, String name, long typeId, String firstName, String lastName, String address, String mobPhone, long carId, String orderCost, String orderStartTime, String orderEndTime, String statusOrder) {
        super(id, name, typeId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.carId = carId;
        this.orderCost = orderCost;
        this.orderStartTime = orderStartTime;
        this.orderEndTime = orderEndTime;
        this.statusOrder = statusOrder;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
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
        return getCarId() == that.getCarId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getMobPhone(), that.getMobPhone()) &&
                Objects.equals(getOrderCost(), that.getOrderCost()) &&
                Objects.equals(getOrderStartTime(), that.getOrderStartTime()) &&
                Objects.equals(getOrderEndTime(), that.getOrderEndTime()) &&
                Objects.equals(getStatusOrder(), that.getStatusOrder());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getAddress(), getMobPhone(), getCarId(), getOrderCost(), getOrderStartTime(), getOrderEndTime(), getStatusOrder());
    }

    @Override
    public String toString() {
        return OrderEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", carId=" + carId +
                ", orderCost='" + orderCost + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", statusOrder='" + statusOrder + '\'';
    }
}
