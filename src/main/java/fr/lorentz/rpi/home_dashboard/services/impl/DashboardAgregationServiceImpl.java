package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.model.DashboardModel;
import fr.lorentz.rpi.home_dashboard.model.DateableStructure;
import fr.lorentz.rpi.home_dashboard.services.*;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                .meteo(meteoService.retrieve(LocalDate.now()))
                .dateableStructure(IntStream
                        .range(0, nextDaysCount)
                        .mapToObj(LocalDate.now()::plusDays)
                        .map(date -> Triple.of(
                                date,
                                repasService.atDay(date),
                                meetingService.atDay(date)))
                        .map(pair -> DateableStructure
                                .builder()
                                .date(pair.getLeft())
                                .repas(pair.getMiddle())
                                .agenda(pair.getRight())
                                .build())
                        .toList())
                .build();
    }
}
