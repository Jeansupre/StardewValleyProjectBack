package com.jean.stardew_valley_api.exceptions;

import com.jean.stardew_valley_api.util.ITools;

import java.io.Serial;
import java.io.Serializable;

public class DataNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -2581841443672094089L;

    public DataNotFoundException() {
        super(ITools.getMensaje("DATA_NOT_FOUND_ERROR"));
    }

    public DataNotFoundException(String aMensaje) {
        super(aMensaje);
    }

    public DataNotFoundException(String aMensaje, boolean aPosibilidadContinuar) {
        super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
    }

    public DataNotFoundException(String aMensaje, Throwable aCausa) {
        super(aMensaje, aCausa);
    }

    public DataNotFoundException(String aMensaje, Exception aException) {
        super(aMensaje, aException);
    }

    public DataNotFoundException(String aMensaje, Throwable aCausa, boolean enableSuppression, boolean writableStackTrace) {
        super(aMensaje, aCausa, enableSuppression, writableStackTrace);
    }
}
