package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;
import ru.vsu.dao.Dao;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.mappers.ObjectMapper;
import ru.vsu.services.serviceImpl.ParamsService;
import ru.vsu.services.serviceImpl.ReferenceService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Repository
public class ObjectDao<T extends ObjectEntity> implements Dao<ObjectEntity> {
    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE = "DELETE  FROM  eav.object WHERE eav.object.id = ?";
    private static final String INSERT = "INSERT INTO  eav.object VALUES (DEFAULT ,?,?)";
    private static final String INSERT_AND_RETURN_ID = "INSERT INTO  eav.object VALUES (DEFAULT ,?,?) RETURNING id";
    private static final String UPDATE = "UPDATE eav.object SET name = ? WHERE eav.object.id = ?";
    private static final String GET_ALL = "SELECT * FROM  eav.object";
    private static final String GET_BY_ID = "SELECT * FROM  eav.object WHERE eav.object.id = ?";
    private static final String GET_LIST_OF_OBJECT_ID = "SELECT eav.object.id FROM  eav.object WHERE eav.object.object_type_id = ?";

    private ParamsService paramsService;
    private ReferenceService referenceService;

    @Autowired
    public ObjectDao(JdbcTemplate jdbcTemplate, ParamsService paramsService, ReferenceService referenceService) {
        this.jdbcTemplate = jdbcTemplate;
        this.paramsService = paramsService;
        this.referenceService = referenceService;
    }

    @Override
    public void delete(ObjectEntity obj) {
        jdbcTemplate.update(DELETE, obj.getId());
    }

    public void deleteByObjectId(long objectId) {
        jdbcTemplate.update(DELETE, objectId);
    }

    @Override
    public long insert(ObjectEntity obj) {
        return jdbcTemplate.update(INSERT, obj.getName(), obj.getTypeId());
    }

    public Long insertAndReturnId(ObjectEntity obj) {
        return jdbcTemplate.queryForObject(INSERT_AND_RETURN_ID, Long.class, obj.getName(), obj.getTypeId());
    }

    @Override
    public void update(ObjectEntity obj) {
        jdbcTemplate.update(UPDATE, obj.getName(), obj.getId());
    }

    @Override
    public List<ObjectEntity> getAll() {
        List<ObjectEntity> list = jdbcTemplate.query(GET_ALL, new ObjectMapper());
        return list;
    }

    public ObjectEntity getObjectEntityById(long id) {
        if (id == 0) return null;
        ObjectEntity objectEntity = jdbcTemplate.queryForObject(GET_BY_ID, new ObjectMapper(), id);
        return objectEntity;
    }

    public List<Long> getListOfObjectIdByObjectTypeId(long objectTypeId) {
        return jdbcTemplate.queryForList(GET_LIST_OF_OBJECT_ID, Long.class, objectTypeId);

    }

    /**
     * Метод позволяет собрать объект наследующийся от ObjectEntity с помощью рефлексии
     *
     * @param id          - айдишник объекта в бд
     * @param classEntity класс требуемого объекта
     * @return
     */
    public T findById(long id, Class classEntity) {
        T newEntity = null;
        //создаем мапу содержащую в себе пары attrId : value
        Map<Long, String> attributeValueMap = paramsService.getParamsMapByObjectId(id);
        //создаем мапу содержащую в себе пары attrId : reference (где reference - айдишник объекта на который ссылаемся по attrId)
        Map<Long, Long> attributeReferenceMap = referenceService.getReferenceMapByObjectId(id);
        //создаем экзмепляр объекта суперкласса
        ObjectEntity objectEntity = getObjectEntityById(id);
        if (objectEntity == null) return null;
        try {
            //создаем экзмепляр объекта
            newEntity = (T) classEntity.getDeclaredConstructor().newInstance();
            newEntity.setId(id);
            newEntity.setName(objectEntity.getName());
            newEntity.setTypeId(objectEntity.getTypeId());
            //дергаем все обявленные поля(в том числе приватные) и идем по ним форичем
            for (Field field : classEntity.getDeclaredFields()) {
                //если встречена интересующая нас аннотация заходим в иф
                if (field.isAnnotationPresent(ParamAttributeId.class)) {
                    //отключаем проверку джавы на доступность полей
                    field.setAccessible(true);
                    // объекту newEntity ставим в качестве значения поля значение взятое из мапы по айди аннотации атрибутайди
                    field.set(newEntity, attributeValueMap.get(field.getAnnotation(ParamAttributeId.class).id()));
                }
                //если встречена интересующая нас аннотация заходим в иф
                if (field.isAnnotationPresent(Reference.class)) {
                    //отключаем проверку джавы на доступность полей
                    field.setAccessible(true);
                    // объекту newEntity ставим в качестве значения поля значение лежащие в аннотации
                    if (!(attributeReferenceMap.get(field.getAnnotation(Reference.class).attrId()) == null)) {
                        field.set(newEntity, attributeReferenceMap.get(field.getAnnotation(Reference.class).attrId()));
                    } else {
                        field.set(newEntity, 0);//если не нашли в мапе ссылок, ставим полю значение 0
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return newEntity;
    }

}
