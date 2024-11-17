package fr.lorentz.rpi.home_dashboard.model;

import java.time.LocalDate;

public record Repas(
        LocalDate date,
        String description
) implements DateSortable {
}
