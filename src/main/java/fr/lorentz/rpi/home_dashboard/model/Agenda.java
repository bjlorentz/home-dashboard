package fr.lorentz.rpi.home_dashboard.model;

import java.time.LocalDate;

public record Agenda(
        LocalDate date,
        String evenement
) implements DateSortable {
    
}
