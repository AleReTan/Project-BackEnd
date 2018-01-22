package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.ObjectDao;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class ObjectService<T extends ObjectEntity> implements MyService<ObjectEntity> {
    private ObjectDao<T> objectDao;

    @Autowired
    public ObjectService(ObjectDao<T> objectDao) {
        this.objectDao = objectDao;
    }

    @Override
    public void delete(ObjectEntity obj) {
        objectDao.delete(obj);
    }

    @Override
    public void insert(ObjectEntity obj) {
        objectDao.insert(obj);
    }

    @Override
    public void update(ObjectEntity obj) {
        objectDao.update(obj);
    }

    @Override
    public List<ObjectEntity> getAll() {
        return objectDao.getAll();
    }

    public ObjectEntity getObjectEntityById(long id) {
        return objectDao.getObjectEntityById(id);
    }

    public T findById(long id, Class classEntity) {
        return objectDao.findById(id, classEntity);
    }

    public List<Long> getListOfObjectIdByObjectTypeId(long objectTypeId) {
        return objectDao.getListOfObjectIdByObjectTypeId(objectTypeId);
    }

    public Long getObjectIdByNameAndObjectTypeId(String name, long objectTypeId) {
        return objectDao.getObjectIdByNameAndObjectTypeId(name, objectTypeId);
    }
}
