package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.Meteo;
import fr.lorentz.rpi.home_dashboard.services.MeteoService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class MeteoServiceImpl implements MeteoService {

    Collection<Meteo> dbMeteo = new ArrayList<>();

    @PostConstruct
    public void init() {
        dbMeteo.add(new Meteo(LocalDate.now(), "bi-sun", 0.0, 10.1));
        dbMeteo.add(new Meteo(LocalDate.now().plusDays(1L), "bi-wind", 3.3, 12.1));
        dbMeteo.add(new Meteo(LocalDate.now().plusDays(2L), "bi-cloud", -4.3, 6.2));
        dbMeteo.add(new Meteo(LocalDate.now().plusDays(3L), "bi-cloud-rain", 3.2, 8.7));
        dbMeteo.add(new Meteo(LocalDate.now().plusDays(4L), "bi-snow", -4.3, -0.4));
    }

    @Override
    public Meteo retrieve(LocalDate date) {
        return dbMeteo.stream()
                .filter(it -> it.date().equals(date))
                .findFirst()
                .orElse(new Meteo(date, null, null, null));
    }
}
