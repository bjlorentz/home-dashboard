package fr.lorentz.rpi.home_dashboard.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.WritableResource;

@Getter
@AllArgsConstructor
public class DatasourceConfig {
    private WritableResource resource;
}
