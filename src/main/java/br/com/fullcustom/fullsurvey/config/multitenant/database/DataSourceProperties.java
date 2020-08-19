package br.com.fullcustom.fullsurvey.config.multitenant.database;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tenants")
public class DataSourceProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceProperties.class);

    private Map<Object, Object> datasources = new LinkedHashMap<>();

    public Map<Object, Object> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, Map<String, String>> datasources) {
        datasources.forEach((key, value) -> this.datasources.put(key, convert(value)));
    }
    
    public DataSource convert(Map<String, String> source) {

        LOGGER.debug("Creating Datasource by properties: " + source);

        return DataSourceBuilder.create()
            .url(source.get("jdbcUrl"))
            .driverClassName(source.get("driverClassName"))            
            .username(source.get("username"))
            .password(source.get("password"))            
            .build();
    }
}