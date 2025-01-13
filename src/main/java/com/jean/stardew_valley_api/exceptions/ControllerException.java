package com.jean.stardew_valley_api.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jean.stardew_valley_api.dto.ErrorDTO;
import com.jean.stardew_valley_api.util.ITools;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class ControllerException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = -281862736013412672L;

    private static final String TIPO_WARNING = "warning";
    private static final String TIPO_ERROR = "error";

    /**
     * Se lanza cuando los parámetros de entrada al contralador son inválidos, es
     * decir cuando se comparan con las anotaciones del Mapeo y resulta inválido al
     * ingresar como RequestBody a la capa
     *
     * @param request: petición del contexto para atrapar si va hacia alguna página
     * @param e        excepcion que se lanza
     * @return ResponseEntity<>
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorDTO> invalidoObjetoDeEntrada(HttpServletRequest request,
                                                            MethodArgumentNotValidException e) {

        final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

        // obtiene errores spring
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        // convierte los mensajes a una cadena legible
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage
                .append(f.getField()).append(" ").append(f.getDefaultMessage()).append(" "));

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(),
                        errorMessage.toString(), TIPO_WARNING, request.getRequestURI(), false),
                codigoHttp);
    }

    /**
     * Atrapa excepciones de conversión de datos
     *
     * @param request petición del contexto para atrapar
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorDTO> invalidoConversionTipoDato(HttpServletRequest request,
                                                               MethodArgumentTypeMismatchException e,
                                                               HandlerMethod handlerMethod) {

        final HttpStatus codigoHttp = HttpStatus.METHOD_NOT_ALLOWED;

        saveExceptionLog(e, handlerMethod, true);

        return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), ITools.getMensaje("INCORRECT_TYPE_DATA"),
                TIPO_ERROR, request.getRequestURI(), false), codigoHttp);
    }

    /**
     * Atrapa excepciones de tamaño en adjuntos
     *
     * @param request petición del contexto para atrapar
     */
    @ExceptionHandler({MaxUploadSizeExceededException.class, SizeLimitExceededException.class,
            MultipartException.class})
    public ResponseEntity<ErrorDTO> maxSizeException(HttpServletRequest request, Exception e,
                                                     HandlerMethod handlerMethod) {

        final HttpStatus codigoHttp = HttpStatus.EXPECTATION_FAILED;

        saveExceptionLog(e, handlerMethod, true);

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(),
                        ITools.getMensaje("SIZE_UPLOAD_FILE_EXCEEDED"), TIPO_ERROR,
                request.getRequestURI(), false), codigoHttp);
    }

    /**
     * Transacción no exitosa cuando incumple anotaciones o validaciones de mapeo
     * eje @NotNull o @Length
     *
     * @param request petición del contexto para atrapar
     */
    @ExceptionHandler({jakarta.validation.ConstraintViolationException.class,
            TransactionSystemException.class,
            HttpMessageNotReadableException.class,
            InvalidFormatException.class,
            HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ErrorDTO> transaccionNoExitosaIncumpleValidacionesMapeo(HttpServletRequest request) {

        final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(),
                        ITools.getMensaje("INVALID_VALUES_FOR_REQUEST"), TIPO_ERROR,
                        request.getRequestURI(), false),
                codigoHttp);
    }

    /**
     * Atrapa excepciones de transacción no exitosa por errores en la base de datos
     */
    @ExceptionHandler({SQLException.class})
    public ResponseEntity<ErrorDTO> transaccionNoExitosaErroresDB(HttpServletRequest request, Exception e) {

        final HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(),
                        ITools.getMensaje("TRANSACTION_ERROR"), TIPO_ERROR,
                        request.getRequestURI(), false),
                codigoHttp);
    }


    /**
     * Atrapa valores nulos en capa de servicio
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointerException(HttpServletRequest request, NullPointerException e,
                                                         HandlerMethod handlerMethod) {
        saveExceptionLog(e, handlerMethod, true);

        return new ResponseEntity<>(
                new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ITools.getMensaje("NULL_VALUES"), TIPO_ERROR, request.getRequestURI(),
                        false),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // errores que pueden ser lanzados por programador

    /**
     * Esta excepción se relanza al cliente cuando hay una validación de negocio que
     * se incumple
     */
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<ErrorDTO> incumpleValidacionNegocio(HttpServletRequest request, ValidacionException e) {
        final HttpStatus codigoHttp = HttpStatus.CONFLICT;

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(), e.getMessage(),
                        TIPO_ERROR, request.getRequestURI(), false),
                codigoHttp);
    }

    /**
     * Esta excepción se relanza al cliente cuando hay una transacción no exitosa
     * que alguien quiera relanzar o un error técnico
     */
    @ExceptionHandler({TechnicalException.class, IOException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorDTO> transaccioNoExitosaOErrorTecnicoAtrapado(Exception e,
                                                                             HandlerMethod handlerMethod) {
        final HttpStatus codigoHttp = HttpStatus.CONFLICT;

        saveExceptionLog(e, handlerMethod, false);

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(), e.getMessage(),
                        TIPO_ERROR, null, false),
                codigoHttp);
    }

    /**
     * Esta excepción se relanza al cliente cuando hay un dato que no se encuentra
     */
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<ErrorDTO> dataNotFound(DataNotFoundException e, HandlerMethod handlerMethod) {
        final HttpStatus codigoHttp = HttpStatus.NOT_FOUND;

        saveExceptionLog(e, handlerMethod, false);

        return new ResponseEntity<>(new ErrorDTO(codigoHttp.value(), e.getMessage(), TIPO_ERROR, null, false),
                codigoHttp);
    }

    /**
     * Atrapa excepciones de no autorización
     */
    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ErrorDTO> unauthorized(HttpServletRequest request, AuthException e) {
        final HttpStatus codigoHttp = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<>(
                new ErrorDTO(codigoHttp.value(), e.getMessage(),
                        TIPO_ERROR, request.getRequestURI(), false),
                codigoHttp);
    }


    /**
     * * Guarda log de la excepción.
     *
     * @param e: exception y su información de clase de servcio,
     * @param handlerMethod: información que viene desde la capa controladora
     * @param aExceptionContainer: si viene del contenedor o no controlada es true y
     *                             si manual del usuario es false
     */
    private void saveExceptionLog(Exception e, HandlerMethod handlerMethod, boolean aExceptionContainer) {
        String claseConPaquete;
        String metodo;
        String linea;
        String error;

        // capa de servicio o clase que lo lanza
        if (e != null) {
            claseConPaquete = e.getStackTrace()[0].getClassName();
            metodo = e.getStackTrace()[0].getMethodName();
            linea = "" + e.getStackTrace()[0].getLineNumber();
            error = aExceptionContainer ? e.toString() : e.getMessage();

            writeExceptionLogFile(claseConPaquete, metodo, linea, error);

        }

        // desde el origen padre (capac controladora)
        if (handlerMethod != null) {

            claseConPaquete = handlerMethod.getMethod().getDeclaringClass().getName();
            metodo = handlerMethod.getMethod().getName();
            linea = null;
            error = null;

            writeExceptionLogFile(claseConPaquete, metodo, linea, error);
        }
    }

    /**
     * Escribe en disco el log con la estructura definida en xml,
     */
    private void writeExceptionLogFile(String aClaseConPaquete, String aMetodo, String aLinea, String aError) {
        log.error("[CLASE: {}] [METODO: {}] [LINEA: {}] [ERROR: {}]", aClaseConPaquete, aMetodo, aLinea, aError);
    }
}

