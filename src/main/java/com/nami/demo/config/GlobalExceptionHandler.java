package com.nami.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Maneja errores de conversión de parámetros y binding
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, BindException.class})
    public ResponseEntity<Map<String, Object>> handleBindingErrors(Exception ex) {
        Map<String, Object> response = new HashMap<>();

        String message;
        if (ex instanceof MethodArgumentTypeMismatchException mismatch) {
            String name = mismatch.getName();
            String value = mismatch.getValue() != null ? mismatch.getValue().toString() : "null";
            message = String.format("El parámetro '%s' no es válido. Valor recibido: '%s'", name, value);
        } else if (ex instanceof BindException bindEx) {
            message = bindEx.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("Error de binding desconocido");
        } else {
            message = ex.getMessage();
        }

        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("message", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Maneja cualquier RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
