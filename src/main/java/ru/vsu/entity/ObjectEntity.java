package ru.vsu.entity;

import java.util.Objects;

public class ObjectEntity {
    long id;
    String name;
    long typeId;

    public ObjectEntity(long id, String name, long typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    public ObjectEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
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
