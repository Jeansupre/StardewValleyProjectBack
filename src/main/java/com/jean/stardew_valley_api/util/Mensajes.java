package com.jean.stardew_valley_api.util;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Mensajes {

    protected static MessageSource messageSource;

    public String getMensaje(String aEtiqueta) {
        try {
            if (messageSource != null && aEtiqueta != null) {
                return messageSource.getMessage(aEtiqueta, (Object[])null, LocaleContextHolder.getLocale());
            } else {
                return  "[" + aEtiqueta + "]";
            }
        } catch (NoSuchMessageException var3) {
            return "[" + aEtiqueta + "]";
        }
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        Mensajes.messageSource = messageSource;
    }
}
