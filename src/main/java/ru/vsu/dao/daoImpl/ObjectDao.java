package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.mappers.ObjectMapper;

import java.util.List;

@Component
public class ObjectDao implements Dao<ObjectEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ObjectEntity obj) {
        String sql = "Delete  from  eav.object where eav.object.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ObjectEntity obj) {
        String sql = " INSERT into  eav.object VALUES (DEFAULT ,?,?)";
        jdbcTemplate.update(sql, obj.getName(), obj.getTypeId());
    }

    @Override
    public void update(ObjectEntity obj) {
        String sql = " UPDATE eav.object SET eav.object.name = ? WHERE eav.object.id = ?";
        jdbcTemplate.update(sql, obj.getName(), obj.getId());
    }

    @Override
    public List<ObjectEntity> getAll() {
        String sql = "SELECT * FROM  eav.object";
        List<ObjectEntity> list = jdbcTemplate.query(sql, new ObjectMapper());
        return list;
    }
}
