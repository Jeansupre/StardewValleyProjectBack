package com.jean.stardew_valley_api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class PricipalConfig {

    /**
     * Permite modificar la spropiedades del archivo de mensajes.
     */
    @Bean
    public MessageSource messageSource() {
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Permite internacionalización y este se lanza para aquellas valdaciones
     * internas de anotaciones tomando el locale adecuado
     */
    @Bean
    public LocalValidatorFactoryBean getValidator() {
        var bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * Genera un Bean con un LocaleResolver personalizado, éste es encargado de la
     * configuración regional para el archivo messages_XX.properties que va ser
     * tenido en cuenta y también para los mensajes por defecto que tiene el
     * proyecto
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }

}
