package com.jean.stardew_valley_api.exceptions;

import com.jean.stardew_valley_api.util.ITools;

import java.io.Serial;

public class ValidacionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -468711757549941276L;

    public ValidacionException() {
        super(ITools.getMensaje("VALID_ERROR"));
    }

    public ValidacionException(String aMensaje) {
        super(aMensaje);
    }

    public ValidacionException(String aMensaje, boolean aPosibilidadContinuar) {
        super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
    }

    public ValidacionException(String aMensaje, Throwable aCausa) {
        super(aMensaje, aCausa);
    }

    public ValidacionException(String aMensaje, Exception aException) {
        super(aMensaje, aException);
    }

    public ValidacionException(String aMensaje, Throwable aCausa, boolean enableSuppression, boolean writableStackTrace) {
        super(aMensaje, aCausa, enableSuppression, writableStackTrace);
    }
}
