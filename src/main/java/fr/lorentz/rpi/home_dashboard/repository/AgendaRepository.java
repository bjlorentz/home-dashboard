package fr.lorentz.rpi.home_dashboard.repository;

import fr.lorentz.rpi.home_dashboard.model.Agenda;

import java.time.LocalDate;
import java.util.Collection;

public interface AgendaRepository {
    Agenda save(Agenda agenda);

    Collection<Agenda> findAll();

    Collection<Agenda> evenementAt(LocalDate day);
}
