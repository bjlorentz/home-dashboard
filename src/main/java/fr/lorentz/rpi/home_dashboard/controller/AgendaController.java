package fr.lorentz.rpi.home_dashboard.controller;

import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @RequestMapping("/agenda")
    public String agenda(Model model) {
        model.addAttribute("agenda", new Agenda(null, ""));
        model.addAttribute("agendas", agendaRepository.findAll());
        return "agenda";
    }

    @PostMapping("/saveAgenda")
    public String saveAgenda(Agenda agenda) {
        agendaRepository.save(agenda);
        return "redirect:/agenda";
    }
}