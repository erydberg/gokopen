package se.gokopen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.gokopen.dao.ConfigRegistrationDao;
import se.gokopen.model.ConfigRegistration;

@Service
public class ConfigRegistrationService {

    @Autowired
    ConfigRegistrationDao configRegistrationDao;


    @Transactional
    public ConfigRegistration getCurrentConfig(){
        return configRegistrationDao.getCurrentConfigRegistration();
    }

    @Transactional
    public void saveConfigRegistration(ConfigRegistration configRegistration){
        configRegistrationDao.save(configRegistration);
    }
}
