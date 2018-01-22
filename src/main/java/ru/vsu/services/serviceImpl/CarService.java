package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.CarEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.MyAbstractEntityService;

@Service
public class CarService extends MyAbstractEntityService<CarEntity> {
    public CarService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
    }
}
//@SuppressWarnings("Duplicates")
//@Service
//public class CarService implements MyService<CarEntity> {
//    private ObjectService<ObjectEntity> objectService;//или напрямую к дао?(что скорее всего не так)
//    private ParamsService paramsService;
//    private ReferenceService referenceService;
//
//    @Autowired
//    public CarService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
//        this.objectService = objectService;
//        this.paramsService = paramsService;
//        this.referenceService = referenceService;
//    }
//
//    @Override
//    public void delete(CarEntity obj) {
//        objectService.delete(obj);
//        paramsService.deleteByObjectId(obj.getId());
//        referenceService.deleteByObjectId(obj.getId());
//    }
//
//    @Override
//    public void insert(CarEntity obj) {
//        //здесь айдишник объекта увеличивается с помощью сиквенса
//        objectService.insert(obj);
//        long realObjectId = objectService.getObjectIdByNameAndObjectTypeId(obj.getName(), obj.getTypeId());
//        for (Field field : obj.getClass().getDeclaredFields()) {
//            if (field.isAnnotationPresent(ParamAttributeId.class)) {
//                try {
//                    field.setAccessible(true);
//                    //obj.getId - ставит айди того что передано, а не того что было получено дефаултом
//                    paramsService.insert(field.getAnnotation(ParamAttributeId.class).id(), /*obj.getId()*/realObjectId, (String) field.get(obj));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (field.isAnnotationPresent(Reference.class)) {
//                try {
//                    field.setAccessible(true);
//                    //obj.getId - ставит айди того что передано, а не того что было получено дефаултом
//                    referenceService.insert(field.getLong(obj), /*obj.getId()*/realObjectId, field.getAnnotation(Reference.class).attrId());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void update(CarEntity obj) {
//        objectService.update(obj);
//        for (Field field : obj.getClass().getDeclaredFields()) {
//            if (field.isAnnotationPresent(ParamAttributeId.class)) {
//                try {
//                    field.setAccessible(true);
//                    paramsService.update((String) field.get(obj), field.getAnnotation(ParamAttributeId.class).id(), obj.getId());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (field.isAnnotationPresent(Reference.class)) {
//                try {
//                    field.setAccessible(true);
//                    referenceService.update(field.getLong(obj), obj.getId(), field.getAnnotation(Reference.class).attrId());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public List<CarEntity> getAll() {
//        List<CarEntity> carEntities = new ArrayList<>();
//        for (long id :
//                objectService.getListOfObjectIdByObjectTypeId(CarEntity.class.getAnnotation(ObjectTypeId.class).id())) {
//            carEntities.add((CarEntity) objectService.findById(id, CarEntity.class));
//        }
//        return carEntities;
//    }
//
//    public CarEntity getCarById(long id) {
//        return (CarEntity) objectService.findById(id, CarEntity.class);
//    }
//}
