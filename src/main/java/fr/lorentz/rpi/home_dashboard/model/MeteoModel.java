package fr.lorentz.rpi.home_dashboard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(builderClassName = "Builder")
@Getter
@ToString
public final class MeteoModel {
    private final String city;
    private final String minimalTemperature;
    private final String maximaleTemperature;
}
