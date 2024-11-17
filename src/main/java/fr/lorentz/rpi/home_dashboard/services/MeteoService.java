package fr.lorentz.rpi.home_dashboard.services;

import fr.lorentz.rpi.home_dashboard.model.MeteoModel;

import java.time.LocalDate;

public interface MeteoService {
    MeteoModel retrieve(LocalDate date);
}
