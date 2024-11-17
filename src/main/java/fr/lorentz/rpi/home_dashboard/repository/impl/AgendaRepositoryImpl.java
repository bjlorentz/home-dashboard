package fr.lorentz.rpi.home_dashboard.repository.impl;

import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.repository.AgendaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class AgendaRepositoryImpl implements AgendaRepository {

    Collection<Agenda> dbAgenda = new ArrayList<>();

    @PostConstruct
    void init() {
        dbAgenda.add(new Agenda(LocalDate.now().minusDays(1), "Cinéma"));
        dbAgenda.add(new Agenda(LocalDate.now().plusDays(2), "Déplacement Nimes"));
        dbAgenda.add(new Agenda(LocalDate.now().plusDays(4), "Chez les Troncy"));
        dbAgenda.add(new Agenda(LocalDate.now().plusDays(4), "+ Anniversaire"));
        dbAgenda.add(new Agenda(LocalDate.now().minusDays(3), "Concert"));
    }

    @Override
    public Agenda save(Agenda agenda) {
        this.dbAgenda.add(agenda);
        return agenda;
    }

    @Override
    public Collection<Agenda> findAll() {
        return dbAgenda.stream().sorted().toList();
    }

    @Override
    public Collection<Agenda> evenementAt(LocalDate day) {
        List<Agenda> list = dbAgenda.stream().filter(agenda -> agenda.date().equals(day)).toList();
        return list.isEmpty() ? List.of() : list;
    }
}
