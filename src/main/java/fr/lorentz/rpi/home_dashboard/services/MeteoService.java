package fr.lorentz.rpi.home_dashboard.services;

import fr.lorentz.rpi.home_dashboard.model.Meteo;

import java.time.LocalDate;

public interface MeteoService {
    Meteo retrieve(LocalDate date);
}
