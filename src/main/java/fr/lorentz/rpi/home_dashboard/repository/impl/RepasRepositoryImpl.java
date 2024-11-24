package fr.lorentz.rpi.home_dashboard.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lorentz.rpi.home_dashboard.config.DatasourceConfig;
import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.model.Repas;
import fr.lorentz.rpi.home_dashboard.repository.RepasRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Repository
public class RepasRepositoryImpl implements RepasRepository {

    private final DatasourceConfig datasourceConfig;
    private final ObjectMapper marshaller;

    Collection<Repas> dbRepas = new ArrayList<>();

    public RepasRepositoryImpl(@Qualifier("repasDatasource") DatasourceConfig datasourceConfig, ObjectMapper marshaller) {
        this.datasourceConfig = datasourceConfig;
        this.marshaller = marshaller;
    }

    @PostConstruct
    void init() {
        this.dbRepas.addAll(this.load(this.datasourceConfig));
    }

    @Override
    public Repas save(Repas repas) {
        dbRepas.add(repas);
        this.persist(this.datasourceConfig, dbRepas);
        return repas;
    }

    @Override
    public Collection<Repas> findAll() {
        return dbRepas.stream().sorted().toList();
    }

    private Collection<Repas> load(DatasourceConfig datasourceInformation) {
        try {
            if (!datasourceInformation.getResource().exists()
                    && !datasourceInformation.getResource().getFile().createNewFile()) {
                throw new RuntimeException();
            }
            if (datasourceInformation.getResource().contentLength() > 0) {
                return Arrays.stream(marshaller.readValue(datasourceInformation.getResource().getInputStream(), Repas[].class)).toList();
            }
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void persist(DatasourceConfig datasourceInformation, Collection<T> toSave) {
        try (OutputStream out = datasourceInformation.getResource().getOutputStream()) {
            marshaller.writeValue(out, toSave);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
