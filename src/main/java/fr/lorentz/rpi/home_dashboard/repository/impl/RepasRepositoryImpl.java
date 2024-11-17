package fr.lorentz.rpi.home_dashboard.repository.impl;

import fr.lorentz.rpi.home_dashboard.model.Repas;
import fr.lorentz.rpi.home_dashboard.repository.RepasRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class RepasRepositoryImpl implements RepasRepository {

    Collection<Repas> dbRepas = new ArrayList<>();

    @PostConstruct
    void init() {
        this.dbRepas.add(new Repas(LocalDate.now().plusDays(1), "Midi : Poulet"));
        this.dbRepas.add(new Repas(LocalDate.now().plusDays(1), "Soir : Poulet (restes)"));
        this.dbRepas.add(new Repas(LocalDate.now().plusDays(2), "Raclette"));
        this.dbRepas.add(new Repas(LocalDate.now().plusDays(4), "Fondu"));
    }

    @Override
    public Repas save(Repas repas) {
        dbRepas.add(repas);
        return repas;
    }

    @Override
    public Collection<Repas> findAll() {
        return dbRepas.stream().sorted().toList();
    }
}
