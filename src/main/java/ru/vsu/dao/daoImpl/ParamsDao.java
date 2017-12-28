package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.ObjectEntity;
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
//мб некст 2 метода должны быть where p.object_id=? и p.attr_id=? соответственно
    public List<ParamsEntity> getParamsByObjectId(ObjectEntity obj) {
        String sql = "SELECT p.* FROM  eav.params p " +
                "JOIN eav.object o ON p.object_id = o.id " +
                "WHERE o.id = ?";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper(), obj.getId());
        return list;
    }

    public List<ParamsEntity> getParamsByAtributeId(AttributeEntity obj) {
        String sql = "SELECT p.* FROM  eav.params p " +
                "JOIN eav.attribute a ON p.attr_id = a.id " +
                "WHERE a.id = ?";
        List<ParamsEntity> list = jdbcTemplate.query(sql, new ParamsMapper(), obj.getId());
        return list;
    }
}
