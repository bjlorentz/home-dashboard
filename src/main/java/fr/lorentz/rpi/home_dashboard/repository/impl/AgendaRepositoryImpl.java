package fr.lorentz.rpi.home_dashboard.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.lorentz.rpi.home_dashboard.config.DatasourceConfig;
import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.repository.AgendaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class AgendaRepositoryImpl implements AgendaRepository {

    @Value("${service.datasource.agenda}")
    private WritableResource agendaDatasource;

    private final DatasourceConfig datasourceConfig;
    private final ObjectMapper marshaller;

    Collection<Agenda> dbAgenda = new ArrayList<>();

    public AgendaRepositoryImpl(@Qualifier("agendaDatasource") DatasourceConfig datasourceConfig, ObjectMapper marshaller) {
        this.marshaller = marshaller;
        this.datasourceConfig = datasourceConfig;
    }

    @PostConstruct
    public void init() {
        dbAgenda.addAll(this.load(this.datasourceConfig));
    }

    @Override
    public Agenda save(Agenda agenda) {
        this.dbAgenda.add(agenda);
        this.persist(this.datasourceConfig, dbAgenda);
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

    private Collection<Agenda> load(DatasourceConfig datasourceInformation) {
        try {
            if (!datasourceInformation.getResource().exists()
                    && !datasourceInformation.getResource().getFile().createNewFile()) {
                throw new RuntimeException();
            }
            if (datasourceInformation.getResource().contentLength() > 0) {
                return Arrays.stream(marshaller.readValue(datasourceInformation.getResource().getInputStream(), Agenda[].class)).toList();
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
