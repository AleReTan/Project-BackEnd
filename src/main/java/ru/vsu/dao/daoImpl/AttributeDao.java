package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.mappers.AttributeMapper;

import java.util.List;

@Repository
public class AttributeDao implements Dao<AttributeEntity> {
    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE = "DELETE FROM eav.attribute WHERE eav.attribute.id = ?";
    private static final String INSERT = "INSERT INTO eav.attribute VALUES (DEFAULT ,?,?,?,?)";
    private static final String UPDATE = "UPDATE eav.attribute SET name = ? WHERE eav.attribute.id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.attribute";
    private static final String GET_BY_ID = "SELECT * FROM  eav.attribute WHERE eav.attribute.id = ?";

    @Autowired
    public AttributeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(AttributeEntity obj) {
        jdbcTemplate.update(DELETE, obj.getId());
    }

    @Override
    public void insert(AttributeEntity obj) {
        jdbcTemplate.update(INSERT, obj.getName(), obj.getObjectTypeId(), obj.getValueType(), obj.isRequire());
    }

    @Override
    public void update(AttributeEntity obj) {
        jdbcTemplate.update(UPDATE, obj.getName(), obj.getId());
    }

    @Override
    public List<AttributeEntity> getAll() {
        List<AttributeEntity> list = jdbcTemplate.query(GET_ALL, new AttributeMapper());
        return list;
    }

    public AttributeEntity getAttributeEntityById(long id) {
        AttributeEntity attributeEntity = jdbcTemplate.queryForObject(GET_BY_ID, new AttributeMapper(), id);
        return attributeEntity;
    }
}
