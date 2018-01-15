package ru.vsu.entity;


import ru.vsu.annotation.AttributeId;
import ru.vsu.annotation.ObjectTypeId;

import java.util.Objects;

@ObjectTypeId(id = 6)
public class OrderEntity extends ObjectEntity {

    @AttributeId(id = 1)
    private String firstName;

    @AttributeId(id = 2)
    private String lastName;

    @AttributeId(id = 3)
    private String address;

    @AttributeId(id = 4)
    private String mobPhone;

    @AttributeId(id = 5)
    private String modelCar;

    @AttributeId(id = 6)
    private String color;

    @AttributeId(id = 7)
    private String numberAuto;

    @AttributeId(id = 8)
    private String orderCost;

    @AttributeId(id = 9)
    private String orderStartTime;

    @AttributeId(id = 10)
    private String orderEndTime;

    @AttributeId(id = 11)
    private String statusOrder;


    public OrderEntity() {

    }

    public OrderEntity(long id, String name, long typeId, String firstName, String lastName, String address, String mobPhone, String modelCar, String color, String numberAuto, String orderCost, String orderStartTime, String orderEndTime, String statusOrder) {
        super(id, name, typeId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.modelCar = modelCar;
        this.color = color;
        this.numberAuto = numberAuto;
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

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumberAuto() {
        return numberAuto;
    }

    public void setNumberAuto(String numberAuto) {
        this.numberAuto = numberAuto;
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
        return Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getMobPhone(), that.getMobPhone()) &&
                Objects.equals(getModelCar(), that.getModelCar()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getNumberAuto(), that.getNumberAuto()) &&
                Objects.equals(getOrderCost(), that.getOrderCost()) &&
                Objects.equals(getOrderStartTime(), that.getOrderStartTime()) &&
                Objects.equals(getOrderEndTime(), that.getOrderEndTime()) &&
                Objects.equals(getStatusOrder(), that.getStatusOrder());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getAddress(), getMobPhone(), getModelCar(), getColor(), getNumberAuto(), getOrderCost(), getOrderStartTime(), getOrderEndTime(), getStatusOrder());
    }

    @Override
    public String toString() {
        return OrderEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", modelCar='" + modelCar + '\'' +
                ", color='" + color + '\'' +
                ", numberAuto='" + numberAuto + '\'' +
                ", orderCost='" + orderCost + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderEndTime='" + orderEndTime + '\'' +
                ", statusOrder='" + statusOrder + '\'';
    }
}
