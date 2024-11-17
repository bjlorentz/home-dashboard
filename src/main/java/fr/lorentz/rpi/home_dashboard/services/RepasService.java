package fr.lorentz.rpi.home_dashboard.services;

import fr.lorentz.rpi.home_dashboard.model.Repas;

import java.time.LocalDate;
import java.util.Collection;

public interface RepasService {

    Collection<Repas> atDay(LocalDate date);
}
