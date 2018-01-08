package ru.vsu.entity;

import java.math.BigInteger;
import java.util.Objects;

public class ParamsEntity {
    BigInteger id;
    BigInteger attrId;
    BigInteger objectId;
    String value;

    public ParamsEntity(BigInteger id, BigInteger attrId, BigInteger objectId, String value) {
        this.id = id;
        this.attrId = attrId;
        this.objectId = objectId;
        this.value = value;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getAttrId() {
        return attrId;
    }

    public void setAttrId(BigInteger attrId) {
        this.attrId = attrId;
    }

    public BigInteger getObjectId() {
        return objectId;
    }

    public void setObjectId(BigInteger objectId) {
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
                " id=" + id +
                ", attrId=" + attrId +
                ", objectId=" + objectId +
                ", value='" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamsEntity that = (ParamsEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAttrId(), that.getAttrId()) &&
                Objects.equals(getObjectId(), that.getObjectId()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getAttrId(), getObjectId(), getValue());
    }
}
