package teh.dev.companyportal.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import teh.dev.companyportal.api.CompanyApi;
import teh.dev.companyportal.providers.PreMatchingRequestFilter;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CompanyApi.class);
        register(PreMatchingRequestFilter.class);
    }
}