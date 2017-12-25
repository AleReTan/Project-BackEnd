package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ParamsEntity;
import ru.vsu.entity.mappers.ParamsMapper;

import java.util.List;

@Repository
public class ParamsDao implements Dao<ParamsEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParamsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ParamsEntity obj) {
        String sql = "Delete  from  eav.params where eav.params.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ParamsEntity obj) {
        String sql = " INSERT into  eav.params VALUES (DEFAULT ,?,?,?)";
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
}
