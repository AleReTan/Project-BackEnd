package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.ObjectTypeDao;
import ru.vsu.entity.ObjectTypeEntity;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class ObjectTypeService implements MyService<ObjectTypeEntity>{
    private ObjectTypeDao objectTypeDao;

    @Autowired
    public ObjectTypeService(ObjectTypeDao objectTypeDao) {
        this.objectTypeDao = objectTypeDao;
    }

    @Override
    public void delete(ObjectTypeEntity obj) {
        objectTypeDao.delete(obj);
    }

    @Override
    public long insert(ObjectTypeEntity obj) {
        return objectTypeDao.insert(obj);
    }

    @Override
    public void update(ObjectTypeEntity obj) {
        objectTypeDao.update(obj);
    }

    @Override
    public List<ObjectTypeEntity> getAll() {
        return objectTypeDao.getAll();
    }
}
