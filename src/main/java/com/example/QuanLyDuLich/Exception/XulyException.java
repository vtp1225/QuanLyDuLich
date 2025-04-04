package com.example.QuanLyDuLich.Exception;

import com.example.QuanLyDuLich.dto.Request.ApiRespone;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class XulyException {
    private static final String MIN_ATTRIBUTE= "min";
 /*@ExceptionHandler(value = RuntimeException.class)
     ResponseEntity<ApiRespone> xulyRunTime (RuntimeException exception)
    {
        ApiRespone apiRespone=new ApiRespone();
        apiRespone.setCode(ErrorCode.UNCATE_EXCEPTION.getCode());
        apiRespone.setMessage(ErrorCode.UNCATE_EXCEPTION.getMessage());
        return ResponseEntity.status(ErrorCode.UNCATE_EXCEPTION.getHttpStatusCode()).body(apiRespone);
    }*/
    @ExceptionHandler(value = AppExceptions.class)
    ResponseEntity<ApiRespone> xulyAppexception (AppExceptions exception)
    {
        ErrorCode errorCode=exception.getErrorCode();
        ApiRespone apiRespone=new ApiRespone();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage() );
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiRespone);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> xulyMTAN(MethodArgumentNotValidException exception)
    {
        Map<String,Object> attributes=null;
        ErrorCode errorCode =ErrorCode.INVALID_KEY;
        String enumkey = (String) exception.getFieldError().getDefaultMessage();
        try {
            errorCode = ErrorCode.valueOf(enumkey);
            var constrainViolation= exception.getBindingResult()
                    .getAllErrors().getFirst().unwrap(ConstraintViolation.class);
            attributes = constrainViolation.getConstraintDescriptor().getAttributes();

        }
        catch (IllegalArgumentException e)
        {

        }
        ApiRespone api= new ApiRespone();
        api.setMessage(
                Objects.nonNull(attributes)?
                        mapAttribute(errorCode.getMessage(),attributes):
                        errorCode.getMessage()
        );
        api.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(api);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    ResponseEntity<ApiRespone> xulyConstraint(ConstraintViolationException exception)
    {

        ErrorCode errorCode =ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf((String) exception.getMessage());
        }
        catch (IllegalArgumentException e)
        {

        }
        ApiRespone api= new ApiRespone();
        api.setMessage(errorCode.getMessage());
        api.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(api);
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiRespone> xulyAcessDenied (AccessDeniedException exception)
    {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiRespone apiRespone=new ApiRespone();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiRespone);

    }

    private String mapAttribute(String message, Map<String, Object> attributes)
    {
        var atribute = String.valueOf(attributes.get(MIN_ATTRIBUTE));
         return message.replace("{"+MIN_ATTRIBUTE+"}",atribute);
    }
}
