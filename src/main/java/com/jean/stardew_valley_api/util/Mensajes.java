package com.jean.stardew_valley_api.util;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@RequiredArgsConstructor
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
}
