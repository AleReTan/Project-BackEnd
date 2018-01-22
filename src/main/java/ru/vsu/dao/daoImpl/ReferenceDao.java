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

    @Autowired
    public ReferenceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ReferenceEntity obj) {
        String sql = "DELETE  FROM  eav.reference WHERE eav.reference.object_id = ? AND eav.reference.attr_id=?";
        jdbcTemplate.update(sql, obj.getObjectId(), obj.getAttrId());
    }

    public void deleteByObjectId(long objectId) {
        String sql = "DELETE  FROM  eav.reference WHERE eav.reference.object_id = ?";
        jdbcTemplate.update(sql, objectId);
    }

    @Override
    public void insert(ReferenceEntity obj) {
        String sql = " INSERT INTO  eav.reference VALUES (? ,?, ?)";
        jdbcTemplate.update(sql, obj.getReference(), obj.getObjectId(), obj.getAttrId());
    }

    public void insert(long reference, long objectId, long attributeId) {
        String sql = " INSERT INTO  eav.reference VALUES (? ,?, ?)";
        jdbcTemplate.update(sql, reference, objectId, attributeId);
    }

    @Override
    public void update(ReferenceEntity obj) {
        String sql = " UPDATE eav.reference SET reference = ? WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";
        jdbcTemplate.update(sql, obj.getReference(), obj.getObjectId(), obj.getAttrId());
    }

    public void update(long newReference, long objectId, long attributeId) {
        String sql = " UPDATE eav.reference SET reference = ? WHERE eav.reference.object_id = ? AND eav.reference.attr_id = ?";
        jdbcTemplate.update(sql, newReference, objectId, attributeId);
    }

    @Override
    public List<ReferenceEntity> getAll() {
        String sql = "SELECT * FROM  eav.reference";
        List<ReferenceEntity> list = jdbcTemplate.query(sql, new ReferenceMapper());
        return list;
    }

    public List<ReferenceEntity> getReferencesByObjectId(long objectId) {
        String sql = "SELECT * FROM  eav.reference WHERE eav.reference.object_id = ?";
        List<ReferenceEntity> list = jdbcTemplate.query(sql, new ReferenceMapper(), objectId);
        return list;
    }

    public List<ReferenceEntity> getReferencesByRefId(long refId) {
        String sql = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ?";
        List<ReferenceEntity> list = jdbcTemplate.query(sql, new ReferenceMapper(), refId);
        return list;
    }

    public ReferenceEntity getReferencesByRefIdAndObjectId(long refId, long objectId) {
        String sql = "SELECT * FROM  eav.reference WHERE eav.reference.reference = ? AND eav.reference.object_id";
        ReferenceEntity referenceEntity = jdbcTemplate.queryForObject(sql, new ReferenceMapper(), refId, objectId);
        return referenceEntity;
    }

    public Map<Long, Long> getReferenceMapByObjectId(long objectId) {
        String sql = "SELECT * FROM  eav.reference r WHERE r.object_id = ?";
        List<ReferenceEntity> list = jdbcTemplate.query(sql, new ReferenceMapper(), objectId);
        Map<Long, Long> returnMap = new HashMap<>();
        for (ReferenceEntity referenceEntity : list) {
            returnMap.put(referenceEntity.getAttrId(), referenceEntity.getReference());
        }
        return returnMap;
    }

}
