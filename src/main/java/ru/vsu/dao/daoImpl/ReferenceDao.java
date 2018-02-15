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
    private static final String INSERT = "INSERT INTO  eav.reference VALUES (? ,?, ?)";
    private static final String UPDATE = "UPDATE eav.reference SET reference = ? WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.reference";
    private static final String GET_BY_OBJECT_ID = "SELECT * FROM  eav.reference WHERE eav.reference.object_id = ?";
    private static final String GET_BY_REFERENCE = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ?";
    private static final String GET_BY_REFERENCE_AND_OBJECT_ID = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ? AND eav.reference.object_id";
    private static final String GET_MAP_BY_OBJECT_ID = "SELECT * FROM  eav.reference r WHERE r.object_id = ?";

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

    @Override
    public void insert(ReferenceEntity obj) {
        jdbcTemplate.update(INSERT, obj.getReference(), obj.getObjectId(), obj.getAttrId());
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

    public ReferenceEntity getReferencesByRefIdAndObjectId(long refId, long objectId) {
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

}
