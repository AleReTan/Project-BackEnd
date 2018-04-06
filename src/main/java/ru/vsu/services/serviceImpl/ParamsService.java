package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.ParamsDao;
import ru.vsu.entity.ParamsEntity;
import ru.vsu.services.MyService;

import java.util.List;
import java.util.Map;

@Service
public class ParamsService implements MyService<ParamsEntity> {
    private ParamsDao paramsDao;

    @Autowired
    public ParamsService(ParamsDao paramsDao) {
        this.paramsDao = paramsDao;
    }

    @Override
    public void delete(ParamsEntity obj) {
        paramsDao.delete(obj);
    }

    public void deleteByObjectId(long deleteByObjectId) {
        paramsDao.deleteByObjectId(deleteByObjectId);
    }

    @Override
    public void insert(ParamsEntity obj) {
        paramsDao.insert(obj);
    }

    public void insert(long attributeId, long objectId, String value) {
        paramsDao.insert(attributeId, objectId, value);
    }

    @Override
    public void update(ParamsEntity obj) {
        paramsDao.update(obj);
    }

    public void update(String newValue, long attributeId, long objectId) {
        paramsDao.update(newValue, attributeId, objectId);
    }

    @Override
    public List<ParamsEntity> getAll() {
        return paramsDao.getAll();
    }

    public ParamsEntity getParamsEntityByObjectIdAndAttributeId(long objectId, long attrId) {
        return paramsDao.getParamsEntityByObjectIdAndAttributeId(objectId, attrId);
    }

    public Map<Long, String> getParamsMapByObjectId(long objectId) {
        return paramsDao.getParamsMapByObjectId(objectId);
    }

    public long getObjectIdByAttributeIdAndValue(long attrId, String value) {
        return paramsDao.getObjectIdByAttributeIdAndValue(attrId, value);
    }
}
