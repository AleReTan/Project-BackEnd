package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.entity.Phone;
import ru.vsu.dao.daoImpl.PhoneDao;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class PhoneService implements MyService<Phone> {
    private PhoneDao phoneDao;

    @Autowired
    public PhoneService(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public void delete(Phone obj) {
        phoneDao.delete(obj);
    }

    @Override
    public void insert(Phone obj) {
        phoneDao.insert(obj);
    }

    @Override
    public void update(Phone obj) {
        phoneDao.update(obj);
    }

    @Override
    public List<Phone> getAll() {
        return phoneDao.getAll();
    }
}
