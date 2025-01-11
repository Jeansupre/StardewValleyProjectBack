package com.jean.stardew_valley_api.util;

public interface ITools {
    static String getMensaje(String aEtiqueta) {
        return (new Mensajes()).getMensaje(aEtiqueta);
    }
}
