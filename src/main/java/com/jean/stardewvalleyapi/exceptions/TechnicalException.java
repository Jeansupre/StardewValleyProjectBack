package com.jean.stardewvalleyapi.exceptions;

import com.jean.stardewvalleyapi.util.ITools;

import java.io.Serial;

public class TechnicalException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 5841953166572722091L;

    public TechnicalException() {
        super(ITools.getMensaje("TECH_ERROR"));
    }

    public TechnicalException(String aMensaje) {
        super(aMensaje);
    }

    public TechnicalException(String aMensaje, boolean aPosibilidadContinuar) {
        super(aMensaje, aPosibilidadContinuar ? new Throwable("true") : null);
    }

    public TechnicalException(String aMensaje, Throwable aCausa) {
        super(aMensaje, aCausa);
    }

    public TechnicalException(String aMensaje, Exception aException) {
        super(aMensaje, aException);
    }

    public TechnicalException(String aMensaje, Throwable aCausa, boolean enableSuppression, boolean writableStackTrace) {
        super(aMensaje, aCausa, enableSuppression, writableStackTrace);
    }
}
