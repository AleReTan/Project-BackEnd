package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.ObjectTypeEntity;
import ru.vsu.entity.ReferenceEntity;
import ru.vsu.entity.mappers.AttributeMapper;

import java.util.List;

@Repository
public class AttributeDao implements Dao<AttributeEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttributeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(AttributeEntity obj) {
        String sql = "DELETE  FROM  eav.attribute WHERE eav.attribute.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(AttributeEntity obj) {
        String sql = " INSERT INTO  eav.attribute VALUES (DEFAULT ,?,?,?,?)";
        jdbcTemplate.update(sql, obj.getName(), obj.getObjectTypeId(), obj.getValueType(), obj.isRequire());
    }

    @Override
    public void update(AttributeEntity obj) {
        String sql = " UPDATE eav.attribute SET eav.attribute.name = ? WHERE eav.attribute.id = ?";
        jdbcTemplate.update(sql, obj.getName(), obj.getId());
    }

    @Override
    public List<AttributeEntity> getAll() {
        String sql = "SELECT * FROM  eav.attribute";
        List<AttributeEntity> list = jdbcTemplate.query(sql, new AttributeMapper());
        return list;
    }

    public AttributeEntity getAttributeEntityById(long id) {
        String sql = "SELECT * FROM  eav.attribute WHERE eav.attribute.id = ?";
        AttributeEntity attributeEntity = jdbcTemplate.queryForObject(sql, new AttributeMapper(), id);
        return attributeEntity;
    }

    public List<AttributeEntity> getAttributesByEntityObjectTypeId(ObjectTypeEntity obj) {
        String sql = "SELECT a.* FROM  eav.attribute a " +
                "JOIN eav.object_type ot ON a.object_type_id = ot.id " +
                "WHERE ot.id = ?";
        List<AttributeEntity> list = jdbcTemplate.query(sql, new AttributeMapper(), obj.getId());
        return list;
    }

    public List<AttributeEntity> getAttributesByEntityObjectId(ObjectEntity obj) {
        String sql = "SELECT a.* FROM  eav.attribute a " +
                "JOIN eav.object_type ot ON a.object_type_id = ot.id " +
                "JOIN eav.object o ON ot.id = o.object_type_id " +
                "WHERE o.id = ?";
        List<AttributeEntity> list = jdbcTemplate.query(sql, new AttributeMapper(), obj.getId());
        return list;
    }

    public List<AttributeEntity> getAttributesByEntityReferenceRefId(ReferenceEntity obj) {
        String sql = "SELECT a.* FROM  eav.attribute a " +
                "JOIN eav.reference r ON a.id = r.attr_id " +
                "WHERE r.reference = ?";
        List<AttributeEntity> list = jdbcTemplate.query(sql, new AttributeMapper(), obj.getReference());
        return list;
    }
}
