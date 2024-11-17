package fr.lorentz.rpi.home_dashboard.controller;

import fr.lorentz.rpi.home_dashboard.model.Agenda;
import fr.lorentz.rpi.home_dashboard.model.Repas;
import fr.lorentz.rpi.home_dashboard.repository.RepasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RepasController {

    @Autowired
    private RepasRepository repasRepository;

    @RequestMapping("/repas")
    public String agenda(Model model) {
        model.addAttribute("repas", new Repas(null, ""));
        model.addAttribute("repass", repasRepository.findAll());
        return "repas";
    }

    @PostMapping("/saveRepas")
    public String saveAgenda(Repas repas) {
        repasRepository.save(repas);
        return "redirect:/repas";
    }
}