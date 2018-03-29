package ru.vsu.entity;

import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

import java.util.Objects;

@ObjectTypeId(id = 8)
public class DriverEntity extends ObjectEntity {

    @ParamAttributeId(id = 1)
    private String firstName;

    @ParamAttributeId(id = 2)
    private String lastName;

    @ParamAttributeId(id = 4)
    private String phoneNumber;

    @ParamAttributeId(id = 23)
    private String driverGeoData;

    @Reference(attrId = 16)
    private long carId;

    @ParamAttributeId(id = 25)
    private String onShift;

    public DriverEntity() {
    }

    public DriverEntity(long id, String name, long typeId, String firstName, String lastName, String phoneNumber, String driverGeoData, long carId, String onShift) {
        super(id, name, typeId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.driverGeoData = driverGeoData;
        this.carId = carId;
        this.onShift = onShift;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDriverGeoData() {
        return driverGeoData;
    }

    public void setDriverGeoData(String driverGeoData) {
        this.driverGeoData = driverGeoData;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getOnShift() {
        return onShift;
    }

    public void setOnShift(String onShift) {
        this.onShift = onShift;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DriverEntity that = (DriverEntity) o;
        return getCarId() == that.getCarId() &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
                Objects.equals(getDriverGeoData(), that.getDriverGeoData()) &&
                Objects.equals(getOnShift(), that.getOnShift());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getPhoneNumber(), getDriverGeoData(), getCarId(), getOnShift());
    }

    @Override
    public String toString() {
        return DriverEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", driverGeoData='" + driverGeoData + '\'' +
                ", onShift='" + onShift + '\'' +
                ", carId=" + carId;
    }
}
