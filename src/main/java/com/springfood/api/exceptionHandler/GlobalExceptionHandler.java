package com.springfood.api.exceptionHandler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.springfood.domain.exception.BadRequestException;
import com.springfood.domain.exception.ConflictException;
import com.springfood.domain.exception.NotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.TYPE_INTERNAL_SERVER_ERRO;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, "Erro interno na aplicação, caso o erro persista contatar o admin do sistema")
                .build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);

    }

    //EXCEPTIONS CUSTOMIZADAS
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<?> handleNotFoundException(NotFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.TYPE_NOT_FOUND;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, e.getMessage())
                .build();

        return this.handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<?> handleBadRequestException(BadRequestException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, e.getMessage()).build();

        return this.handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<?> handleConflictException(ConflictException e, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.TYPE_CONFLICT;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, e.getMessage()).build();

        return this.handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {

        HttpStatus status = HttpStatus.FORBIDDEN;
        ProblemType problemType = ProblemType.TYPE_FORBIDEN;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, "Você não tem permissão para acessar este recurso")
                .userMessage(e.getMessage())
                .build();

        return this.handleExceptionInternal(e, problemDetails, new HttpHeaders(), status, request);
    }


    //EXCEPTIONS SOBRESCRITAS

    //Captura exceptions que herdam HttpMessageNotReadableException, e retorna o erro conforme o seu tipo
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        ProblemDetails problemDetails = getProblemDetails(problemType, status, ex.getMessage()).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        if (ex instanceof MethodArgumentTypeMismatchException) {

            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, request, status);
        }

        ProblemDetails problemDetails = getProblemDetails(problemType, status, ex.getMessage()).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.TYPE_NOT_FOUND;

        String detail = String.format("A uri '%s' que você tentou acessar não existe", ex.getRequestURL());

        ProblemDetails problemDetails = getProblemDetails(problemType, status, detail).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleInternalException(ex, status, request, ex.getBindingResult());
    }

    /*
     * Configura o retorno padrão de todas das exceptions.
     * */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        if (body == null) {
            body = ProblemDetails
                    .builder()
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = ProblemDetails
                    .builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }


        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request, HttpStatusCode status) {

        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        String detail = String.format("O parametro %s, recebeu o valor '%s' que é inválido, informe um tipo compativel requerido '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        ProblemDetails problemDetails = getProblemDetails(problemType, status, detail).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

        String detail = String.format("A propriedade %s, recebeu o valor '%s' que é inválido, o tipo compativel requerido é %s",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        ProblemDetails problemDetails = getProblemDetails(problemType, status, detail).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemType problemType = ProblemType.TYPE_BAD_REQUEST;

        String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

        String detail = String.format("A propriedade %s, não existe, verifique as propriedades na documentação e enviei novamente",
                path);

        ProblemDetails problemDetails = getProblemDetails(problemType, status, detail).build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    //Retorna um probemDetails com as prorpiedades default, podendo ser adicionadas novas por quem usa-la
    private ProblemDetails.ProblemDetailsBuilder getProblemDetails(ProblemType problemType, HttpStatusCode status, String detail) {
        return ProblemDetails.builder()
                .type(problemType.getPath())
                .title(problemType.getTitle())
                .detail(detail)
                .timestamp(LocalDateTime.now())
                .status(status.value());
    }

    private ResponseEntity<Object> handleInternalException(Exception ex, HttpStatusCode status, WebRequest request, BindingResult bindingResult) {
        ProblemType problemType = ProblemType.TYPE_NOT_FOUND;

        List<ProblemDetails.Field> fields = bindingResult.getFieldErrors().stream().map(fieldError -> {

            //Obtem as mensagems do messages.properties
            String message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

            return ProblemDetails.Field
                    .builder()
                    .field(fieldError.getField())
                    .message(message)
                    .build();
        }).collect(Collectors.toList());

        ProblemDetails problemDetails = getProblemDetails(problemType, status, "Um ou mais campos estão inválidos, verique os campos e tente novamente")
                .fields(fields)
                .build();

        return this.handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }
}
