package se.gokopen.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import se.gokopen.model.Config;

import java.util.List;

@Repository
public class ConfigDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Config config){
        sessionFactory.getCurrentSession().saveOrUpdate(config);
    }
    
    @SuppressWarnings("unchecked")
    public Config getCurrentConfig(){
        Config config;
        List<Config> configs = sessionFactory.getCurrentSession().createQuery("from Config").list();
        if(configs==null||configs.size()==0){
            config = new Config();
            config.setName("Gök Open");
        }else{
            config = configs.get(0);
        }
        return config;
    }
}
