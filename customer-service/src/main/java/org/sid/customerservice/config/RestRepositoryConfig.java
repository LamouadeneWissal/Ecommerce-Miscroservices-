package org.sid.customerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.CorsConfigurationAware;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.io.Serializable;
@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfigurer config , CorsConfigurationAware)
}
