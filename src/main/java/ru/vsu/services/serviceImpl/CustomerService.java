package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.CustomerEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.AbstractEntityService;

@Service
public class CustomerService extends AbstractEntityService<CustomerEntity> {
    private static final long CUSTOMER_LOGIN_ATTRIBUTE = 27;

    @Autowired
    public CustomerService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, AttributeService attributeService) {
        super(objectService, paramsService, referenceService, attributeService);
    }

    public long getCustomerIdByLogin(String login) {
        return paramsService.getObjectIdByAttributeIdAndValue(CUSTOMER_LOGIN_ATTRIBUTE, login);
    }
}
