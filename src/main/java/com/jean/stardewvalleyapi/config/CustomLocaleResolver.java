package com.jean.stardewvalleyapi.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        return validHeaderAcceptLanguage(headerLang, request);
    }

    /**
     * Evalúa si el header está presente y no está vacío usaremos la configuración
     * regional presente en el header, de lo contrario usaremos el idioma
     * establecido por defecto
     *
     * @param headerLanguage -
     */
    private Locale validHeaderAcceptLanguage(String headerLanguage, HttpServletRequest request) {
        var localeDefault = Locale.of("es", "CO");
        if (headerLanguage.isEmpty()) {
            return localeDefault;
        }
        return request.getLocale();
    }
}
