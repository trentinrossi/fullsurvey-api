package br.com.fullcustom.fullsurvey.config.multitenant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class HeaderTenantInterceptor implements WebRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderTenantInterceptor.class);

    public static final String TENANT_HEADER = "X-tenant";

    @Override
    public void preHandle(WebRequest request) throws Exception {
        LOGGER.debug("Interceptor in action: " + request.getHeader(TENANT_HEADER));
        ThreadTenantStorage.setTenantId(request.getHeader(TENANT_HEADER));
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        ThreadTenantStorage.clear();
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}