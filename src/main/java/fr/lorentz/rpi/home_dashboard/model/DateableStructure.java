package fr.lorentz.rpi.home_dashboard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collection;

@Builder(builderClassName = "Builder")
@ToString
@Getter
public class DateableStructure {
    private LocalDate date;
    private Collection<Agenda> agenda;
    private Collection<Repas> repas;
    private Meteo meteo;
}
