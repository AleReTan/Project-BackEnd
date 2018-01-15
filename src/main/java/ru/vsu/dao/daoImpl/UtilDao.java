package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vsu.annotation.AttributeId;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.facade.ServiceFacade;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class UtilDao<T extends ObjectEntity> {
    private ServiceFacade serviceFacade;

    @Autowired
    public UtilDao(ServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
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

        try {
            newEntity = (T) classEntity.getDeclaredConstructor().newInstance();//создаем экзмепляр объекта
            newEntity.setId(id);
            newEntity.setName(serviceFacade.getObjectService().getObjectEntityById(id).getName());//получаем имя с помощью дао через сервис-фасад
            newEntity.setTypeId(serviceFacade.getObjectService().getObjectEntityById(id).getTypeId());//получаем typeId с помощью дао через сервис-фасад
            for (Field field : classEntity.getDeclaredFields()) { //дергаем все обявленные поля(в том числе приватные) и идем по ним форичем
                if (field.isAnnotationPresent(AttributeId.class)) { //если встречена интересующая нас аннотация заходим в иф
                    /*This method cannot be used to enable access to private members, members
                     with default (package) access, protected instance members, or protected constructors when the declaring
                     class is in a different module to the caller and the package containing the declaring class is not
                      open to the caller's module.
                    */
                    field.setAccessible(true);//отключаем проверку джавы на доступность полей
                    field.set(newEntity,
                            serviceFacade.getParamsService().getParamsEntityByObjectIdAndAttributeId(
                                    id,
                                    field.getAnnotation(AttributeId.class).id()
                            ).getValue()
                    );// объекту newEntity ставим в качестве значения поля значение выдернутое с помощью дао, по айди аннотации атрибутайди
                }
            }
            System.out.println(newEntity.toString());
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
