package br.com.fullcustom.fullsurvey.config.multitenant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadTenantStorage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTenantStorage.class);

    private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        LOGGER.trace("Setting tenant id: " + tenantId);
        currentTenant.set(tenantId);
    }

    public static String getTenantId() {
        LOGGER.trace("Request tenant: " + currentTenant.get());
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}