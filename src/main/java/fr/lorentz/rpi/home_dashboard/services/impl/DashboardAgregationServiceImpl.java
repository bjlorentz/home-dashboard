package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.*;
import fr.lorentz.rpi.home_dashboard.services.DashboardService;
import fr.lorentz.rpi.home_dashboard.services.MeetingService;
import fr.lorentz.rpi.home_dashboard.services.MeteoService;
import fr.lorentz.rpi.home_dashboard.services.RepasService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.IntStream;

@Service
public class DashboardAgregationServiceImpl implements DashboardService {

    private final MeteoService meteoService;
    private final RepasService repasService;
    private final MeetingService meetingService;

    @Value("${service.day.next}")
    private Integer nextDaysCount;

    public DashboardAgregationServiceImpl(MeteoService meteoService, RepasService repasService, MeetingService meetingService) {
        this.meteoService = meteoService;
        this.repasService = repasService;
        this.meetingService = meetingService;
    }

    @Override
    public DashboardModel generate() {
        return DashboardModel.builder()
                .dateableStructure(IntStream
                        .range(0, nextDaysCount)
                        .mapToObj(LocalDate.now()::plusDays)
                        .map(date -> DateableStructure
                                .builder()
                                .date(date)
                                .repas(repasService.atDay(date))
                                .agenda(meetingService.atDay(date))
                                .meteo(meteoService.retrieve(date))
                                .build())
                        .toList())
                .build();
    }
}
