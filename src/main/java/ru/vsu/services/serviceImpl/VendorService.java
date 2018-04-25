package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.entity.VendorEntity;
import ru.vsu.services.AbstractEntityService;

@Service
public class VendorService extends AbstractEntityService<VendorEntity> {
    private static final long VENDOR_LOGIN_ATTRIBUTE = 27;

    @Autowired
    public VendorService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService, AttributeService attributeService) {
        super(objectService, paramsService, referenceService, attributeService);
    }

    public long getVendorIdByLogin(String login) {
        return paramsService.getObjectIdByAttributeIdAndValue(VENDOR_LOGIN_ATTRIBUTE, login);
    }
}
