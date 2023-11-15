package teh.dev.companyportal.application.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import teh.dev.companyportal.api.CompanyApi;
import teh.dev.companyportal.api.SocialSecurityNumberApi;
import teh.dev.companyportal.application.providers.PreMatchingRequestFilter;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CompanyApi.class);
        register(SocialSecurityNumberApi.class);
        register(PreMatchingRequestFilter.class);
    }
}