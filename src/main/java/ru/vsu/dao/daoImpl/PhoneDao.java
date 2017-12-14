package ru.vsu.dao.daoImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.entity.Phone;
import ru.vsu.entity.mappers.PhoneMapper;
import ru.vsu.dao.Dao;

import java.util.Arrays;
import java.util.List;

@Repository
public class PhoneDao implements Dao<Phone> {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PhoneDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(Phone obj) {
        String sql = "Delete  from  \"Test\".\"Phone\"  where \"Test\".\"Phone\".id = ?";
        jdbcTemplate.update(sql,obj.getId());
    }

    @Override
    public void insert(Phone obj) {
        String sql = " INSERT into  \"Test\".\"Phone\" VALUES (DEFAULT ,?,?)";
        jdbcTemplate.update(sql,obj.getModel(),obj.getPrice());
    }

    @Override
    public void update(Phone obj) {
      /* " UPDATE "Test"."Phone"SET
        String sql = " INSERT into  "Test"."Phone" VALUES (DEFAULT ,?,?);";
         jdbcTemplate.update(sql,obj.getModel(),obj.getPrice());*/
    }

    @Override
    public List<Phone> getAll() {
        String sql =  "SELECT * FROM  \"Test\".\"Phone\"";
        List<Phone> list = jdbcTemplate.query(sql, new PhoneMapper());
        return list;
    }
}
