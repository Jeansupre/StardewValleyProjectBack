package com.jean.stardewvalleyapi.util;

public interface ITools {
    static String getMensaje(String aEtiqueta) {
        return (new Mensajes()).getMensaje(aEtiqueta);
    }
}
