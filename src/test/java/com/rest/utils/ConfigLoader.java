package com.rest.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(ConfigLoader.configLoader == null){
            configLoader = new ConfigLoader();
        }
        return ConfigLoader.configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseurl");
        if(prop != null) return prop;
        else throw new RuntimeException("property 'baseUrl' is not specified in the stg_config.properties file");
    }

    public String getLogin(){
        String prop = properties.getProperty("login");
        if(prop != null) return prop;
        else throw new RuntimeException("property 'username' is not specified in the config.properties file");
    }

    public String getToken(){
        String prop = properties.getProperty("token");
        if(prop != null) return prop;
        else throw new RuntimeException("property 'token' is not specified in the config.properties file");
    }
}
