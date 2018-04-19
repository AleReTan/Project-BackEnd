package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.AttributeDao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class AttributeService implements MyService<AttributeEntity> {
    private AttributeDao attributeDao;


    @Autowired
    public AttributeService(AttributeDao attributeDao) {
        this.attributeDao = attributeDao;
    }

    @Override
    public void delete(AttributeEntity obj) {
        attributeDao.delete(obj);
    }

    @Override
    public long insert(AttributeEntity obj) {
        return attributeDao.insert(obj);
    }

    @Override
    public void update(AttributeEntity obj) {
        attributeDao.update(obj);
    }

    @Override
    public List<AttributeEntity> getAll() {
        return attributeDao.getAll();
    }

    public AttributeEntity getAttributeEntityById(long id)

    {
        return attributeDao.getAttributeEntityById(id);
    }

    /**
     * возвращает, обязательный ли атрибут с указанным id или нет
     *
     * @param id
     * @return
     */
    public boolean getIsRequiredByAttributeId(long id) {
        return attributeDao.getIsRequiredByAttributeId(id);
    }
}
