package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.AttributeEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.ObjectTypeEntity;
import ru.vsu.entity.ReferenceEntity;
import ru.vsu.entity.mappers.ObjectMapper;

import java.util.List;

@Repository
public class ObjectDao implements Dao<ObjectEntity> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(ObjectEntity obj) {
        String sql = "DELETE  FROM  eav.object WHERE eav.object.id = ?";
        jdbcTemplate.update(sql, obj.getId());
    }

    @Override
    public void insert(ObjectEntity obj) {
        String sql = " INSERT INTO  eav.object VALUES (DEFAULT ,?,?)";
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

    public ObjectEntity getObjectEntityById(long id) {
        String sql = "SELECT * FROM  eav.object WHERE eav.object.id = ?";
        ObjectEntity objectEntity = jdbcTemplate.queryForObject(sql, new ObjectMapper(), id);
        return objectEntity;
    }

    //TODO:оттестить
    public List<ObjectEntity> getObjectsByEntityObjectTypeId(ObjectTypeEntity obj) {
        //String sql = "SELECT * FROM  eav.object WHERE eav.object.type_id = ?";
        String sql = "SELECT * FROM  eav.object o " +
                "JOIN eav.object_type ot ON o.object_type_id = ot.id " +
                "WHERE ot.id = ?";
        List<ObjectEntity> list = jdbcTemplate.query(sql, new ObjectMapper(), obj.getId());
        return list;
    }

    public List<ObjectEntity> getObjectsByEntityAttrId(AttributeEntity obj) {
        String sql = "SELECT o.* FROM  eav.object o " +
                "JOIN eav.params p ON o.id = p.object_id " +
                "JOIN eav.attribute a ON p.attr_id = a.id " +
                "WHERE a.id = ?";
        List<ObjectEntity> list = jdbcTemplate.query(sql, new ObjectMapper(), obj.getId());
        return list;
    }

    //хз, чет не так, наверное? хочу получить все машины у конкретного водителя
    public List<ObjectEntity> getObjectsByEntityReferenceRefId(ReferenceEntity obj) {
        String sql = "SELECT o.* FROM  eav.object o " +
                "JOIN eav.reference r ON o.id = r.reference " +
                "WHERE r.reference = ?";
        List<ObjectEntity> list = jdbcTemplate.query(sql, new ObjectMapper(), obj.getReference());
        return list;
    }

}
