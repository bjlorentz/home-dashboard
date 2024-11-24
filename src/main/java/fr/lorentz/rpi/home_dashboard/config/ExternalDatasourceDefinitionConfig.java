package fr.lorentz.rpi.home_dashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class ExternalDatasourceDefinitionConfig {

    @Bean(name = "agendaDatasource")
    public DatasourceConfig agendaDatasource(@Value("${service.datasource.agenda}") WritableResource rsrc) {
        return new DatasourceConfig(rsrc);
    }

    @Bean(name = "repasDatasource")
    public DatasourceConfig repasDatasource(@Value("${service.datasource.repas}") WritableResource rsrc) {
        return new DatasourceConfig(rsrc);
    }

}
