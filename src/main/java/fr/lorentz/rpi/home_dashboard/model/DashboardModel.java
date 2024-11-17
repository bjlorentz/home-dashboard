package fr.lorentz.rpi.home_dashboard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Builder(builderClassName = "Builder")
@Getter
@ToString
public class DashboardModel {
    private MeteoModel meteo;
    private Collection<DateableStructure> dateableStructure;
}
