package ru.vsu.entity;

import ru.vsu.annotation.AttributeId;
import ru.vsu.annotation.ObjectTypeId;

import java.util.Objects;

@ObjectTypeId(id = 7)
public class CarEntity extends ObjectEntity {

    @AttributeId(id = 12)
    private String number;

    @AttributeId(id = 13)
    private String model;

    @AttributeId(id = 14)
    private String color;
    
    @AttributeId(id = 15)
    private String type;

    public CarEntity() {
    }

    public CarEntity(long id, String name, long typeId, String number, String model, String color, String type) {
        super(id, name, typeId);
        this.number = number;
        this.model = model;
        this.color = color;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(getNumber(), carEntity.getNumber()) &&
                Objects.equals(getModel(), carEntity.getModel()) &&
                Objects.equals(getColor(), carEntity.getColor()) &&
                Objects.equals(getType(), carEntity.getType());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getNumber(), getModel(), getColor(), getType());
    }

    @Override
    public String toString() {
        return CarEntity.class.getSimpleName() +
                " id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId + '\'' +
                ", number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'';
    }
}
