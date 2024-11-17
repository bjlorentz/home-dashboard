package fr.lorentz.rpi.home_dashboard.controller;

import fr.lorentz.rpi.home_dashboard.services.RefreshService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refresh")
public class ImageGenerationController {

    private final RefreshService refreshService;

    public ImageGenerationController(RefreshService refreshService) {
        this.refreshService = refreshService;
    }

    @GetMapping
    public ResponseEntity<Void> generate() throws Exception {
        refreshService.perform();
        return ResponseEntity.ok().build();
    }
}
