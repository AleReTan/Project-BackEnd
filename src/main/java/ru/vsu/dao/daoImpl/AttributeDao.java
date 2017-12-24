package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.mappers.AttributeMapper;

import java.util.List;

@Component
public class AttributeDao implements Dao<AttributeEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttributeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(AttributeEntity obj) {
        String sql = "Delete  from  eav.attribute where eav.attribute.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(AttributeEntity obj) {
        String sql = " INSERT into  eav.attribute VALUES (DEFAULT ,?,?,?,?)";
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
}
