package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ReferenceEntity;
import ru.vsu.entity.mappers.ReferenceMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReferenceDao implements Dao<ReferenceEntity> {
    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE = "DELETE  FROM  eav.reference WHERE eav.reference.object_id = ? AND eav.reference.attr_id=?";
    private static final String DELETE_BY_OBJECT_ID = "DELETE  FROM  eav.reference WHERE eav.reference.object_id = ?";
    private static final String DELETE_BY_OBJECT_ID_AND_ATTRIBUTE_ID = "DELETE  FROM  eav.reference WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";
    private static final String INSERT = "INSERT INTO  eav.reference VALUES (? ,?, ?)";
    private static final String UPDATE = "UPDATE eav.reference SET reference = ? WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.reference";
    private static final String GET_BY_OBJECT_ID = "SELECT * FROM  eav.reference WHERE eav.reference.object_id = ?";
    private static final String GET_BY_REFERENCE = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ?";
    private static final String GET_OBJECT_ID_BY_REFERENCE_AND_ATTRIBUTE_ID = "SELECT eav.reference.object_id FROM  eav.reference WHERE eav.reference.reference = ? AND eav.reference.attr_id = ?";
    private static final String GET_BY_REFERENCE_AND_OBJECT_ID = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ? AND eav.reference.object_id";
    private static final String GET_MAP_BY_OBJECT_ID = "SELECT * FROM  eav.reference r WHERE r.object_id = ?";
    private static final String GET_BY_REFERENCE_AND_ATTRIBUTE_ID = "SELECT count(*) FROM  eav.reference WHERE eav.reference.reference = ? AND eav.reference.attr_id = ?";
    private static final String GET_BY_OBJECT_ID_AND_ATTRIBUTE_ID = "SELECT count(*) FROM  eav.reference WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";

    @Autowired
    public ReferenceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ReferenceEntity obj) {
        jdbcTemplate.update(DELETE, obj.getObjectId(), obj.getAttrId());
    }

    public void deleteByObjectId(long objectId) {
        jdbcTemplate.update(DELETE_BY_OBJECT_ID, objectId);
    }

    public void deleteByObjectAndAttributeId(long objectId, long attributeId) {
        jdbcTemplate.update(DELETE_BY_OBJECT_ID_AND_ATTRIBUTE_ID, objectId, attributeId);
    }

    @Override
    public long insert(ReferenceEntity obj) {
        return jdbcTemplate.update(INSERT, obj.getReference(), obj.getObjectId(), obj.getAttrId());
    }

    public void insert(long reference, long objectId, long attributeId) {
        jdbcTemplate.update(INSERT, reference, objectId, attributeId);
    }

    @Override
    public void update(ReferenceEntity obj) {
        jdbcTemplate.update(UPDATE, obj.getReference(), obj.getObjectId(), obj.getAttrId());
    }

    public void update(long newReference, long objectId, long attributeId) {
        jdbcTemplate.update(UPDATE, newReference, objectId, attributeId);
    }

    @Override
    public List<ReferenceEntity> getAll() {
        return jdbcTemplate.query(GET_ALL, new ReferenceMapper());
    }

    public List<ReferenceEntity> getReferencesByObjectId(long objectId) {
        return jdbcTemplate.query(GET_BY_OBJECT_ID, new ReferenceMapper(), objectId);
    }

    public List<ReferenceEntity> getReferencesByRefId(long refId) {
        return jdbcTemplate.query(GET_BY_REFERENCE, new ReferenceMapper(), refId);
    }

    public ReferenceEntity getReferenceByRefIdAndObjectId(long refId, long objectId) {
        return jdbcTemplate.queryForObject(GET_BY_REFERENCE_AND_OBJECT_ID, new ReferenceMapper(), refId, objectId);
    }

    public Map<Long, Long> getReferenceMapByObjectId(long objectId) {
        List<ReferenceEntity> list = jdbcTemplate.query(GET_MAP_BY_OBJECT_ID, new ReferenceMapper(), objectId);
        Map<Long, Long> returnMap = new HashMap<>();
        for (ReferenceEntity referenceEntity : list) {
            returnMap.put(referenceEntity.getAttrId(), referenceEntity.getReference());
        }
        return returnMap;
    }

    public long getObjectIdByRefIdAndAttrId(long refId, long attrId) {
        return jdbcTemplate.queryForObject(GET_OBJECT_ID_BY_REFERENCE_AND_ATTRIBUTE_ID, Long.class, refId, attrId);
    }

    /**
     * Метод определяет, существует ли строка в базе по refId и attrId
     * Например: Ищем, ссылается ли кто нибудь на водителя с айди 5(refId = 5) по атрибуту водитель на заказе(attrId = 18)
     * То есть, назначен ли водитель на какой то заказ. Если вернет 0, записей нет, водитель не на заказе.
     * Если вернет 1( по задумке вернуть может только 1 или 0), то запись есть, водитель закреплена за заказом.
     *
     * @param refId  то на кого ссылаемся
     * @param attrId ссылаемся по этому атрибуту
     * @return если запись есть, true, если нет, false
     */
    public boolean isReferenceExistByRefIdAndAttrId(long refId, long attrId) {
        Integer count = jdbcTemplate.queryForObject(GET_BY_REFERENCE_AND_ATTRIBUTE_ID, Integer.class, refId, attrId);
        return count > 0;
    }

    public boolean isReferenceExistByObjectIdAndAttrId(long objectId, long attrId) {
        Integer count = jdbcTemplate.queryForObject(GET_BY_OBJECT_ID_AND_ATTRIBUTE_ID, Integer.class, objectId, attrId);
        return count > 0;
    }
}
