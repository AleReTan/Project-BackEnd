package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.ParamsDao;
import ru.vsu.entity.ParamsEntity;
import ru.vsu.services.MyService;

import java.util.List;

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

    @Override
    public void insert(ParamsEntity obj) {
        paramsDao.insert(obj);
    }

    @Override
    public void update(ParamsEntity obj) {
        paramsDao.update(obj);
    }

    @Override
    public List<ParamsEntity> getAll() {
        return paramsDao.getAll();
    }

    public ParamsEntity getParamsEntityByObjectIdAndAttributeId(long objectId, long attrId) {
        return paramsDao.getParamsEntityByObjectIdAndAttributeId(objectId, attrId);
    }
}
