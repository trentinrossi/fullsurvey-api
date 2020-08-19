package br.com.fullcustom.fullsurvey.config.multitenant.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import br.com.fullcustom.fullsurvey.config.multitenant.web.ThreadTenantStorage;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantRoutingDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        LOGGER.debug("Getting tenantId by currentLookupKey: " + ThreadTenantStorage.getTenantId());
        return ThreadTenantStorage.getTenantId();
    }
}