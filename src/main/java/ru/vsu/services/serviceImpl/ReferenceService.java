package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.ReferenceDao;
import ru.vsu.entity.ReferenceEntity;
import ru.vsu.services.MyService;

import java.util.List;
import java.util.Map;

@Service
public class ReferenceService implements MyService<ReferenceEntity> {
    private ReferenceDao referenceDao;

    @Autowired
    public ReferenceService(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    @Override
    public void delete(ReferenceEntity obj) {
        referenceDao.delete(obj);
    }

    public void deleteByObjectId(long objectId) {
        referenceDao.deleteByObjectId(objectId);
    }

    @Override
    public void insert(ReferenceEntity obj) {
        referenceDao.insert(obj);
    }

    public void insert(long reference, long objectId, long attributeId) {
        referenceDao.insert(reference, objectId, attributeId);
    }

    @Override
    public void update(ReferenceEntity obj) {
        referenceDao.update(obj);
    }

    public void update(long newReference, long objectId, long attributeId) {
        referenceDao.update(newReference, objectId, attributeId);
    }

    @Override
    public List<ReferenceEntity> getAll() {
        return referenceDao.getAll();
    }

    public List<ReferenceEntity> getReferencesByObjectId(long objectId) {
        return referenceDao.getReferencesByObjectId(objectId);
    }

    public List<ReferenceEntity> getReferencesByRefId(long refId) {
        return referenceDao.getReferencesByRefId(refId);
    }

    public ReferenceEntity getReferencesByRefIdAndObjectId(long refId, long objectId) {
        return referenceDao.getReferencesByRefIdAndObjectId(refId, objectId);
    }

    public Map<Long, Long> getReferenceMapByObjectId(long objectId) {
        return referenceDao.getReferenceMapByObjectId(objectId);
    }
}
