package fr.lorentz.rpi.home_dashboard.export;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

class ImageGenerationServiceImplTest {

    @Test
    public void seleniumGeneration() throws Exception {
        // Configuration du WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Benjamin\\Downloads\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1024,768");

        WebDriver driver = new ChromeDriver(options);

        // Chargement de la page HTML avec CSS
        driver.get("http://localhost:8080/dashboard");

        // Capture de la capture d'écran
        File screenshot = ((TakesScreenshot) driver.findElement(By.className("box"))).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new FileOutputStream("output.jpg"));

        // Fermeture du navigateur
        driver.quit();
    }

//    @Test
//    public void flyingSaucerTest() throws Exception {
//        BufferedImage bufferedImage = ImageRenderer.renderToImage("http://localhost:8080/dashboard", "foo.png", 800, 480);
//
//        File file = new File("output.png");
//        ImageIO.write(bufferedImage, "png", file);
//    }

}