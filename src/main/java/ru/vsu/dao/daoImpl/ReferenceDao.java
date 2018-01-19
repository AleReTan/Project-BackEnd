package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ReferenceEntity;
import ru.vsu.entity.mappers.ReferenceMapper;

import java.util.List;

@Repository
public class ReferenceDao implements Dao<ReferenceEntity> {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ReferenceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ReferenceEntity obj) {
        String sql = "Delete  from  eav.reference where eav.reference.reference = ?";
        jdbcTemplate.update(sql,obj.getReference());
    }

    @Override
    public void insert(ReferenceEntity obj) {
        String sql = " INSERT into  eav.reference VALUES (? ,?, ?)";
        jdbcTemplate.update(sql,obj.getReference(),obj.getObjectId(),obj.getAttrId());
    }

    public void insert(long reference, long objectId, long attributeId) {
        String sql = " INSERT INTO  eav.reference VALUES (? ,?, ?)";
        jdbcTemplate.update(sql, reference, objectId, attributeId);
    }

    @Override
    public void update(ReferenceEntity obj) {
        String sql = " UPDATE eav.reference SET eav.reference.object_id = ? WHERE eav.reference.reference = ?";
        jdbcTemplate.update(sql,obj.getObjectId(),obj.getReference());
    }

    @Override
    public List<ReferenceEntity> getAll() {
        String sql =  "SELECT * FROM  eav.reference";
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

}
