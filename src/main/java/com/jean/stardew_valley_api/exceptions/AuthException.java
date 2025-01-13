package com.jean.stardew_valley_api.exceptions;

import com.jean.stardew_valley_api.util.ITools;

import java.io.Serial;

public class AuthException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5841953166572722091L;

    public AuthException() {
        super(ITools.getMensaje("AUTH_ERROR"));
    }

    public AuthException(String aMensaje) {
        super(aMensaje);
    }

    public AuthException(String aMensaje, boolean aPosibilidadContinuar) {
        super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
    }

    public AuthException(String aMensaje, Throwable aCausa) {
        super(aMensaje, aCausa);
    }

    public AuthException(String aMensaje, Exception aException) {
        super(aMensaje, aException);
    }

    public AuthException(String aMensaje, Throwable aCausa, boolean enableSuppression, boolean writableStackTrace) {
        super(aMensaje, aCausa, enableSuppression, writableStackTrace);
    }
}
