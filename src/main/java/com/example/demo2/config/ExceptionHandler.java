package com.example.demo2.config;

import com.example.demo2.base.GeneralException;
import com.example.demo2.base.constant.Code;
import com.example.demo2.base.dto.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        return handleExceptionInternal(e, Code.VALIDATION_ERROR, request);
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler
//    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
//        return handleExceptionInternal(e, Code.VALIDATION_ERROR, request);
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
        return handleExceptionInternal(e, e.getErrorCode(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        return handleExceptionInternal(e, Code.INTERNAL_ERROR, request);
    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
//                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return handleExceptionInternal(ex, Code.valueOf(status), headers, status, request);
//    }


    private ResponseEntity<Object> handleExceptionInternal(Exception e, Code errorCode,
                                                           WebRequest request) {
        return handleExceptionInternal(e, errorCode, HttpHeaders.EMPTY, errorCode.getHttpStatus(),
                request);
    }

    private ResponseEntity<Object> handleExceptionInternal(Exception e, Code errorCode,
                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(
                e,
                ErrorResponseDTO.of(errorCode, errorCode.getMessage(e)),
                headers,
                status,
                request
        );
    }


}