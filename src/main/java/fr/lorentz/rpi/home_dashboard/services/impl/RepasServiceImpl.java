package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.Repas;
import fr.lorentz.rpi.home_dashboard.repository.RepasRepository;
import fr.lorentz.rpi.home_dashboard.services.RepasService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class RepasServiceImpl implements RepasService {

    private final RepasRepository repasRepository;

    public RepasServiceImpl(RepasRepository repasRepository) {
        this.repasRepository = repasRepository;
    }

    @Override
    public Collection<Repas> atDay(LocalDate date) {
        return repasRepository.findAll().stream()
                .filter(repas -> repas.date().equals(date))
                .toList();
    }
}
