package ru.vsu.entity;

import java.util.Objects;

public class VendorOrderEntity {
    private String clientFirstName;
    private String clientLastName;
    private String clientPhoneNumber;
    private String address;
    private String geoData;
    private String destinationGeoData;

    public VendorOrderEntity() {
    }

    public VendorOrderEntity(String clientFirstName, String clientLastName, String clientPhoneNumber, String address, String geoData, String destinationGeoData) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.address = address;
        this.geoData = geoData;
        this.destinationGeoData = destinationGeoData;
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

    public String getGeoData() {
        return geoData;
    }

    public void setGeoData(String geoData) {
        this.geoData = geoData;
    }

    public String getDestinationGeoData() {
        return destinationGeoData;
    }

    public void setDestinationGeoData(String destinationGeoData) {
        this.destinationGeoData = destinationGeoData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorOrderEntity that = (VendorOrderEntity) o;
        return Objects.equals(getClientFirstName(), that.getClientFirstName()) &&
                Objects.equals(getClientLastName(), that.getClientLastName()) &&
                Objects.equals(getClientPhoneNumber(), that.getClientPhoneNumber()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getGeoData(), that.getGeoData()) &&
                Objects.equals(getDestinationGeoData(), that.getDestinationGeoData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getClientFirstName(), getClientLastName(), getClientPhoneNumber(), getAddress(), getGeoData(), getDestinationGeoData());
    }

    @Override
    public String toString() {
        return VendorOrderEntity.class.getSimpleName() +
                " clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", geoData='" + geoData + '\'' +
                ", destinationGeoData='" + destinationGeoData;
    }
}
