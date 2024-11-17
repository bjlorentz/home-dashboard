package fr.lorentz.rpi.home_dashboard.repository;

import fr.lorentz.rpi.home_dashboard.model.Repas;

import java.util.Collection;

public interface RepasRepository {

    Repas save(Repas repas);

    Collection<Repas> findAll();
}
