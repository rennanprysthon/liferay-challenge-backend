package br.com.liferay.controllers;

import br.com.liferay.dto.ErrorResponse;
import br.com.liferay.services.exceptions.ObjectNotFoundException;
import br.com.liferay.services.exceptions.ProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ErrorResponse> objectNotFound(ProjectException e, HttpServletRequest request){
        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> dataIntegrity(Exception e, HttpServletRequest request){
        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro no sistema", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
