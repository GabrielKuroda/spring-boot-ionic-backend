package com.estudos.springudemy.resources.exceptions;

import com.estudos.springudemy.services.execptions.AuthorizationExecption;
import com.estudos.springudemy.services.execptions.DataIntegrityExecption;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundExecption.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExecption e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityExecption.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityExecption e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de Validação",System.currentTimeMillis());
        for(FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(),x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AuthorizationExecption.class)
    public ResponseEntity<StandardError> authorization(AuthorizationExecption e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

}
