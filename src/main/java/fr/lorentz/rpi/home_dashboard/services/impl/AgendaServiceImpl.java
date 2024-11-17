package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.repository.AgendaRepository;
import fr.lorentz.rpi.home_dashboard.services.MeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class AgendaServiceImpl implements MeetingService {

    private static final Logger log = LoggerFactory.getLogger(AgendaServiceImpl.class);

    private final AgendaRepository agendaRepository;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public Agenda insert(LocalDate date, String description) {
        return agendaRepository.save(new Agenda(date, description));
    }

    @Override
    public Collection<Agenda> atDay(LocalDate date) {
        return agendaRepository.evenementAt(date);
    }
}
