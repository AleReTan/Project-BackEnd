package ru.vsu.entity;

import java.util.Objects;

public class ParamsEntity {
    long attrId;
    long objectId;
    String value;

    public ParamsEntity(long attrId, long objectId, String value) {

        this.attrId = attrId;
        this.objectId = objectId;
        this.value = value;
    }

    public long getAttrId() {
        return attrId;
    }

    public void setAttrId(long attrId) {
        this.attrId = attrId;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ParamsEntity.class.getSimpleName() +
                ", attrId=" + attrId +
                ", objectId=" + objectId +
                ", value='" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamsEntity that = (ParamsEntity) o;
        return Objects.equals(getAttrId(), that.getAttrId()) &&
                Objects.equals(getObjectId(), that.getObjectId()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttrId(), getObjectId(), getValue());
    }
}
