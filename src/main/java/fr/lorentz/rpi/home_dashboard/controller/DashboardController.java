package fr.lorentz.rpi.home_dashboard.controller;

import fr.lorentz.rpi.home_dashboard.model.DashboardModel;
import fr.lorentz.rpi.home_dashboard.services.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(Model model) {
        DashboardModel generate = dashboardService.generate();
        log.info("Structure produced : {}", generate);
        model.addAttribute("meteo", generate.getMeteo());
        model.addAttribute("dateable", generate.getDateableStructure());
        return "dashboard_2.html";
    }
}
