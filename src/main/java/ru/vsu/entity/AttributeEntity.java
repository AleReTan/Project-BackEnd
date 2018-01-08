package ru.vsu.entity;

import java.math.BigInteger;
import java.util.Objects;

public class AttributeEntity {

    BigInteger id;
    String name;
    BigInteger objectTypeId;
    String valueType;
    boolean require;

    public AttributeEntity(BigInteger id, String name, BigInteger objectTypeId, String valueType, boolean require) {
        this.id = id;
        this.name = name;
        this.objectTypeId = objectTypeId;
        this.valueType = valueType;
        this.require = require;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(BigInteger objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public boolean isRequire() {
        return require;
    }

    public void setRequire(boolean require) {
        this.require = require;
    }

    @Override
    public String toString() {
        return AttributeEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", objectTypeId=" + objectTypeId +
                ", valueType='" + valueType + '\'' +
                ", require=" + require;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeEntity that = (AttributeEntity) o;
        return isRequire() == that.isRequire() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getObjectTypeId(), that.getObjectTypeId()) &&
                Objects.equals(getValueType(), that.getValueType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getObjectTypeId(), getValueType(), isRequire());
    }
}
