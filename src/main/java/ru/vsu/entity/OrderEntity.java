package ru.vsu.entity;


import ru.vsu.annotation.AttributeId;
import ru.vsu.annotation.ObjectTypeId;

import java.math.BigInteger;
import java.util.Objects;

@ObjectTypeId(id = 1)
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

    //private String orderCost;
    //private String orderStartTime;
    //private String orderEndTime;
    //private String statusOrder;

    @AttributeId(id = 8)
    private String email;//delete

    public OrderEntity() {

    }

    public OrderEntity(BigInteger id, String name, BigInteger typeId, String firstName, String lastName, String address, String mobPhone, String modelCar, String color, String numberAuto, String email) {
        super(id, name, typeId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.modelCar = modelCar;
        this.color = color;
        this.numberAuto = numberAuto;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return getId() == that.getId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getMobPhone(), that.getMobPhone()) &&
                Objects.equals(getModelCar(), that.getModelCar()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getNumberAuto(), that.getNumberAuto()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getMobPhone(), getModelCar(), getColor(), getNumberAuto(), getEmail());
    }

    @Override
    public String toString() {
        return OrderEntity.class.getSimpleName() +
                " id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", modelCar='" + modelCar + '\'' +
                ", color='" + color + '\'' +
                ", numberAuto='" + numberAuto + '\'' +
                ", email='" + email;
    }

}
