package fr.lorentz.rpi.home_dashboard.services.impl;

import fr.lorentz.rpi.home_dashboard.services.RefreshService;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CallScriptRefreshServiceImpl implements RefreshService, ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(CallScriptRefreshServiceImpl.class);

    @Value("${script.ink.refresh.location}")
    private String scriptInkRefreshLocation;

    @Value("${service.chromedriver.location}")
    private String chromeDriverLocation;

    @Value("${service.image.generation.location}")
    private String outputImageLocation;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Refresh system property settings");
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
    }

    @Override
    public void perform() throws Exception {
        generate();
        refresh();
    }

    private void generate() throws IOException {
        // Configuration du WebDriver


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1980,1080");

        WebDriver driver = new ChromeDriver(options);

        // Chargement de la page HTML avec CSS
        driver.get("http://localhost:8080/dashboard");

        // Capture de la capture d'écran
        File screenshot = ((TakesScreenshot) driver.findElement(By.className("box"))).getScreenshotAs(OutputType.FILE);
        rotate(screenshot);

        // Fermeture du navigateur
        driver.quit();
    }

    private void rotate(File file) throws IOException {
        BufferedImage src = ImageIO.read(file);
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(height, width, src.getType());

        Graphics2D graphics2D = dest.createGraphics();
        graphics2D.translate((height - width) / 2, (height - width) / 2);
        graphics2D.rotate(Math.PI / 2, height / 2, width / 2);
        graphics2D.drawRenderedImage(src, null);

        ImageIO.write(dest, "jpg", Path.of(outputImageLocation).toFile());
    }

    private void refresh() {
        try {
            new ProcessBuilder(scriptInkRefreshLocation).start();
        } catch (IOException e) {
            log.error("Failure on refresh screen script execution", e);
        }
    }
}
