package com.fintech.yevhensynii.fintechcourseproject2.config;

import com.fintech.yevhensynii.fintechcourseproject2.errorModel.ErrorMessage;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        log.warn(ex.getClass().getSimpleName(), ex);
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorMessage errorDetails = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);

        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handlePsqlException(SQLException ex,
                                                   WebRequest request) {
        log.warn(ex.getClass().getSimpleName(), ex);
        Map<String, String> response = new HashMap<>();
        response.put("status", "NOT_FOUND");
        response.put("message", "Результат, с введенными данными, не был найден");
        response.put("error", ex.getMessage());

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex,
                                                            WebRequest request) {
        log.warn(ex.getClass().getName(), ex);
        Map<String, String> response = new HashMap<>();
        response.put("status", "BAD_REQUEST");
        response.put("message", "Проверьте корректность введенных данных");
        response.put("error", ex.getMessage());

        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
