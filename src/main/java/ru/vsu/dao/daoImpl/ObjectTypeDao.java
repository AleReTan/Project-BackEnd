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

    @Autowired
    public ObjectTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ObjectTypeEntity obj) {
        String sql = "Delete  from  eav.object_type where eav.object_type.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ObjectTypeEntity obj) {
        String sql = " INSERT into  eav.object_type VALUES (DEFAULT ,?)";
        jdbcTemplate.update(sql, obj.getName());
    }

    @Override
    public void update(ObjectTypeEntity obj) {
        String sql = " UPDATE eav.object_type SET eav.object_type.name = ? WHERE eav.object_type.id = ?";
        jdbcTemplate.update(sql, obj.getName(), obj.getId());
    }

    @Override
    public List<ObjectTypeEntity> getAll() {
        String sql = "SELECT * FROM  eav.object_type";
        List<ObjectTypeEntity> list = jdbcTemplate.query(sql, new ObjectTypeMapper());
        return list;
    }
}
