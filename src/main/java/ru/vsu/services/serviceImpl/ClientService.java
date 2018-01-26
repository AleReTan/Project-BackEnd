package ru.vsu.services.serviceImpl;

import org.springframework.stereotype.Service;
import ru.vsu.entity.ClientEntity;
import ru.vsu.entity.ObjectEntity;
import ru.vsu.services.MyAbstractEntityService;

@Service
public class ClientService extends MyAbstractEntityService<ClientEntity> {
    public ClientService(ObjectService<ObjectEntity> objectService, ParamsService paramsService, ReferenceService referenceService) {
        super(objectService, paramsService, referenceService);
    }
}
