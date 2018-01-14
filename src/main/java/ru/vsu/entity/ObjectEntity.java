package ru.vsu.entity;

import java.math.BigInteger;
import java.util.Objects;

public class ObjectEntity {
    BigInteger id;
    String name;
    BigInteger typeId;

    public ObjectEntity(BigInteger id, String name, BigInteger typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    public ObjectEntity() {
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

    public BigInteger getTypeId() {
        return typeId;
    }

    public void setTypeId(BigInteger typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return ObjectEntity.class.getSimpleName() +
                "  id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectEntity that = (ObjectEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getTypeId(), that.getTypeId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getTypeId());
    }
}
