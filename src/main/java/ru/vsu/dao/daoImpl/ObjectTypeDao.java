package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ObjectTypeEntity;
import ru.vsu.entity.mappers.ObjectTypeMapper;

import java.util.List;

@Repository
public class ObjectTypeDao implements Dao<ObjectTypeEntity> {
    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE = "DELETE  FROM  eav.object_type WHERE eav.object_type.id = ?";
    private static final String INSERT = "INSERT INTO  eav.object_type VALUES (DEFAULT ,?)";
    private static final String UPDATE = "UPDATE eav.object_type SET name = ? WHERE eav.object_type.id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.object_type";

    @Autowired
    public ObjectTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ObjectTypeEntity obj) {
        jdbcTemplate.update(DELETE, obj.getId());
    }

    @Override
    public void insert(ObjectTypeEntity obj) {
        jdbcTemplate.update(INSERT, obj.getName());
    }

    @Override
    public void update(ObjectTypeEntity obj) {
        jdbcTemplate.update(UPDATE, obj.getName(), obj.getId());
    }

    @Override
    public List<ObjectTypeEntity> getAll() {
        return jdbcTemplate.query(GET_ALL, new ObjectTypeMapper());
    }
/*
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
    }*/
}
