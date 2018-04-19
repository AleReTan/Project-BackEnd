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

    public void deleteByReferenceId(long objectId, long attributeId) {
        referenceDao.deleteByObjectAndAttributeId(objectId, attributeId);
    }

    @Override
    public long insert(ReferenceEntity obj) {
        return referenceDao.insert(obj);
    }

    public void insert(long reference, long objectId, long attributeId) {
        if (reference != 0)
            referenceDao.insert(reference, objectId, attributeId);
    }

    @Override
    public void update(ReferenceEntity obj) {
        referenceDao.update(obj);
    }

    public void update(long newReference, long objectId, long attributeId) {
        if (newReference != 0)
            referenceDao.update(newReference, objectId, attributeId);
        else
            deleteByReferenceId(objectId, attributeId);
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

    public ReferenceEntity getReferenceByRefIdAndObjectId(long refId, long objectId) {
        return referenceDao.getReferenceByRefIdAndObjectId(refId, objectId);
    }

    public long getObjectIdByRefIdAndAttrId(long refId, long attrId) {
        return referenceDao.getObjectIdByRefIdAndAttrId(refId, attrId);
    }

    public Map<Long, Long> getReferenceMapByObjectId(long objectId) {
        return referenceDao.getReferenceMapByObjectId(objectId);
    }

    public boolean isReferenceExistByRefIdAndAttrId(long refId, long attrId) {
        return referenceDao.isReferenceExistByRefIdAndAttrId(refId, attrId);
    }

    public boolean isReferenceExistByObjectIdAndAttrId(long objectId, long attrId) {
        return referenceDao.isReferenceExistByObjectIdAndAttrId(objectId, attrId);
    }
}
