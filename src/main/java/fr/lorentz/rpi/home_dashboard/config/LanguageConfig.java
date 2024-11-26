package fr.lorentz.rpi.home_dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LanguageConfig {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localResolver = new SessionLocaleResolver();
        localResolver.setLocaleAttributeName("user_locale"); // Not strictly needed
        localResolver.setDefaultLocale(Locale.FRANCE);
        return localResolver;
    };
}
