package se.gokopen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.dao.ConfigDAO;
import se.gokopen.model.Config;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired ConfigDAO configDao;
    
    @Override
    @Transactional
    public Config getCurrentConfig(){
        return configDao.getCurrentConfig();
    }
    
    @Override
    @Transactional
    public void saveConfig(Config config){
        configDao.save(config);
    }
}
