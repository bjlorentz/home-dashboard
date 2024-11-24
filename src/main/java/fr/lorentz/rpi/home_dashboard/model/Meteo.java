package fr.lorentz.rpi.home_dashboard.model;

import java.time.LocalDate;

public record Meteo(LocalDate date, String weather, Double min, Double max) implements DateSortable {

}
