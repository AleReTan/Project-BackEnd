package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.ParamsEntity;
import ru.vsu.entity.mappers.ParamsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ParamsDao implements Dao<ParamsEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParamsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ParamsEntity obj) {
        String sql = "DELETE  FROM  eav.params WHERE eav.params.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ParamsEntity obj) {
        String sql = " INSERT INTO  eav.params VALUES (DEFAULT ,?,?,?)";
        jdbcTemplate.update(sql, obj.getAttrId(), obj.getObjectId(), obj.getValue());
    }

    @Override
    public void update(ParamsEntity obj) {
        String sql = " UPDATE eav.params SET eav.params.value = ? WHERE eav.params.id = ?";
        jdbcTemplate.update(sql, obj.getValue(), obj.getId());
    }

    @Override
    public List<ParamsEntity> getAll() {
        String sql = "SELECT * FROM  eav.params";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper());
        return list;
    }

    //мб некст 2 метода должны быть where p.object_id=? и p.attr_id=? соответственно
    public List<ParamsEntity> getParamsByEntityObjectId(ObjectEntity obj) {
        String sql = "SELECT p.* FROM  eav.params p " +
                "JOIN eav.object o ON p.object_id = o.id " +
                "WHERE p.object_id = ?";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper(), obj.getId());
        return list;
    }

    public List<ParamsEntity> getParamsByEntityAtributeId(AttributeEntity obj) {
        String sql = "SELECT p.* FROM  eav.params p " +
                "JOIN eav.attribute a ON p.attr_id = a.id " +
                "WHERE p.attr_id = ?";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper(), obj.getId());
        return list;
    }

    public ParamsEntity getParamsEntityByObjectIdAndAttributeId(long objectId, long attrId) {
        String sql = "SELECT * FROM  eav.params p WHERE p.object_id = ? AND p.attr_id = ?";
        ParamsEntity paramsEntity = jdbcTemplate.queryForObject(sql, new ParamsMapper(), objectId, attrId);
        return paramsEntity;
    }

    public Map<Long, String> getParamsMapByObjectId(long objectId) {
        String sql = "SELECT * FROM  eav.params p WHERE p.object_id = ?";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper(), objectId);
        Map<Long, String> returnMap = new HashMap<>();
        for (ParamsEntity paramsEntity : list) {
            returnMap.put(paramsEntity.getAttrId(), paramsEntity.getValue());
        }
        return returnMap;
    }
}
