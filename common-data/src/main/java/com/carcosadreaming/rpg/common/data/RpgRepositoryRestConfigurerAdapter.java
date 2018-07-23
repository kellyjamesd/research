package com.carcosadreaming.rpg.common.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RpgRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter
{
    private static final String BASE_REPOSITORY_URI = "/rpg/api/data";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.setBasePath(BASE_REPOSITORY_URI);
    }
}
