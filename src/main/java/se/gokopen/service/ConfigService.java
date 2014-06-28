package se.gokopen.service;

import org.springframework.transaction.annotation.Transactional;

import se.gokopen.model.Config;

public interface ConfigService {

    public abstract Config getCurrentConfig();

    public abstract void saveConfig(Config config);

}