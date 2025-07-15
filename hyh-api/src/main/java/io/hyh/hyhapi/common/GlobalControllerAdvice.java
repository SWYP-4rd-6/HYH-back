package io.hyh.hyhapi.common;

import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {


    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request) {
        String formattedTimestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        Map<String, Object> response = Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR,
                "resource", request.getRequestURI(),
                "timestamp", formattedTimestamp,
                "code", "SE50000000"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(HyhApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleHyhApplicationException(HyhApplicationException exception, HttpServletRequest request) {
        log.error("Error occurs {}", exception.toString());

        String formattedTimestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        Map<String, Object> response = Map.of(
                "status", exception.getErrorCode().getStatus(),
                "resource", request.getRequestURI(),
                "timestamp", formattedTimestamp,
                "code", exception.getErrorCode().getCode()
        );

        return ResponseEntity.status(exception.getErrorCode().getStatus()).body(response);
    }

}
