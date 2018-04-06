package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ParamsEntity;
import ru.vsu.entity.mappers.ParamsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParamsDao implements Dao<ParamsEntity> {
    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE = "DELETE  FROM  eav.params WHERE" +
            " eav.params.attr_id = ? AND" +
            " eav.params.object_id = ? AND" +
            " eav.params.value = ?";
    private static final String DELETE_BY_OBJECT_ID = "DELETE  FROM  eav.params WHERE eav.params.object_id = ?";
    private static final String INSERT = "INSERT INTO  eav.params VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE eav.params SET value = ? WHERE eav.params.attr_id = ? AND eav.params.object_id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.params";
    private static final String GET_BY_ATTRIBUTE_ID_AND_OBJECT_ID = "SELECT * FROM  eav.params p WHERE p.object_id = ? AND p.attr_id = ?";
    private static final String GET_MAP_BY_OBJECT_ID = "SELECT * FROM  eav.params p WHERE p.object_id = ?";
    private static final String GET_BY_ATTRIBUTE_ID_AND_VALUE = "SELECT eav.params.object_id FROM  eav.params p WHERE p.attr_id = ? AND p.value = ?";
    @Autowired
    public ParamsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ParamsEntity obj) {
        jdbcTemplate.update(DELETE, obj.getAttrId(), obj.getObjectId(), obj.getValue());
    }

    public void deleteByObjectId(long objectId) {
        jdbcTemplate.update(DELETE_BY_OBJECT_ID, objectId);
    }

    @Override
    public void insert(ParamsEntity obj) {
        jdbcTemplate.update(INSERT, obj.getAttrId(), obj.getObjectId(), obj.getValue());
    }

    public void insert(long attributeId, long objectId, String value) {
        jdbcTemplate.update(INSERT, attributeId, objectId, value);
    }

    @Override
    public void update(ParamsEntity obj) {
        jdbcTemplate.update(UPDATE, obj.getValue(), obj.getAttrId(), obj.getObjectId());
    }

    public void update(String newValue, long attributeId, long objectId) {
        jdbcTemplate.update(UPDATE, newValue, attributeId, objectId);
    }

    @Override
    public List<ParamsEntity> getAll() {
        return jdbcTemplate.query(GET_ALL, new ParamsMapper());
    }

    public ParamsEntity getParamsEntityByObjectIdAndAttributeId(long objectId, long attrId) {
        return jdbcTemplate.queryForObject(GET_BY_ATTRIBUTE_ID_AND_OBJECT_ID, new ParamsMapper(), objectId, attrId);
    }

    public Map<Long, String> getParamsMapByObjectId(long objectId) {
        List<ParamsEntity> list = jdbcTemplate.query(GET_MAP_BY_OBJECT_ID, new ParamsMapper(), objectId);
        Map<Long, String> returnMap = new HashMap<>();
        for (ParamsEntity paramsEntity : list) {
            returnMap.put(paramsEntity.getAttrId(), paramsEntity.getValue());
        }
        return returnMap;
    }

    public long getObjectIdByAttributeIdAndValue(long attrId, String value) {
        return jdbcTemplate.queryForObject(GET_BY_ATTRIBUTE_ID_AND_VALUE, Long.class, attrId, value);
    }
}
