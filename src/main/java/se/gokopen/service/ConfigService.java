package se.gokopen.service;

import se.gokopen.model.Config;

public interface ConfigService {

    public abstract Config getCurrentConfig();

    public abstract void saveConfig(Config config);

}