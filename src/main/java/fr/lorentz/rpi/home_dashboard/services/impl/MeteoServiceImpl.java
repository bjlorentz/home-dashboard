package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.MeteoModel;
import fr.lorentz.rpi.home_dashboard.services.MeteoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MeteoServiceImpl implements MeteoService {
    @Override
    public MeteoModel retrieve(LocalDate date) {
        return MeteoModel
                .builder()
                .city("Lyon")
                .minimalTemperature("12.0")
                .maximaleTemperature("27.5")
                .build();
    }
}
