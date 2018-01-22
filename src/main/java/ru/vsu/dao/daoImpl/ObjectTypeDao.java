package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.ObjectTypeEntity;
import ru.vsu.entity.mappers.ObjectTypeMapper;

import java.util.List;

@Repository
public class ObjectTypeDao implements Dao<ObjectTypeEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ObjectTypeEntity obj) {
        String sql = "DELETE  FROM  eav.object_type WHERE eav.object_type.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ObjectTypeEntity obj) {
        String sql = " INSERT INTO  eav.object_type VALUES (DEFAULT ,?)";
        jdbcTemplate.update(sql, obj.getName());
    }

    @Override
    public void update(ObjectTypeEntity obj) {
        String sql = " UPDATE eav.object_type SET name = ? WHERE eav.object_type.id = ?";
        jdbcTemplate.update(sql, obj.getName(), obj.getId());
    }

    @Override
    public List<ObjectTypeEntity> getAll() {
        String sql = "SELECT * FROM  eav.object_type";
        List<ObjectTypeEntity> list = jdbcTemplate.query(sql, new ObjectTypeMapper());
        return list;
    }

    public List<ObjectTypeEntity> getObjectTypesByEntityObjectId(ObjectEntity obj) {
        String sql = "SELECT * FROM  eav.object_type ot " +
                "JOIN eav.object o ON ot.id = o.object_type_id" +
                " WHERE o.id = ?";
        List<ObjectTypeEntity> list = jdbcTemplate.query(sql, new ObjectTypeMapper(), obj.getId());
        return list;
    }

    public List<ObjectTypeEntity> getObjectTypesByEntityAttributeId(AttributeEntity obj) {
        String sql = "SELECT * FROM  eav.object_type ot " +
                "JOIN eav.attribute a ON ot.id = a.object_type_id" +
                " WHERE a.id = ?";
        List<ObjectTypeEntity> list = jdbcTemplate.query(sql, new ObjectTypeMapper(), obj.getId());
        return list;
    }
}
