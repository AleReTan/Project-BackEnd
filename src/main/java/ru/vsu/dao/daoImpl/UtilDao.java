package ru.vsu.dao.daoImpl;

import ru.vsu.entity.ObjectEntity;

import java.math.BigInteger;


public class UtilDao<T extends ObjectEntity> {

    public T findById(BigInteger id, Class classEntity) {
        T newEntity = null;
        try {
            newEntity = (T) classEntity.getDeclaredConstructor().newInstance(); //идея предлагает несколько кетчей, как сделать
            System.out.println(newEntity.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newEntity;
    }
}
