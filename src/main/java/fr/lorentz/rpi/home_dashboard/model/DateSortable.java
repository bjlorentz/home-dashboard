package fr.lorentz.rpi.home_dashboard.model;

import java.time.LocalDate;

public interface DateSortable extends Comparable<DateSortable> {

    LocalDate date();

    @Override
    default int compareTo(DateSortable o) {
        return this.date().compareTo(o.date());
    }
}
