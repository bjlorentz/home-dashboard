package fr.lorentz.rpi.home_dashboard.services;

import fr.lorentz.rpi.home_dashboard.model.Agenda;

import java.time.LocalDate;
import java.util.Collection;

public interface MeetingService {

    Agenda insert(LocalDate date, String description);

    Collection<Agenda> atDay(LocalDate date);
}
