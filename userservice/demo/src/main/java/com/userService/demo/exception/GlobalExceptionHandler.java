package com.userService.demo.exception;

import com.userService.demo.dto.ExceptionDto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleRoleNotFoundException(RoleNotFoundException roleNotFoundException){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.NOT_FOUND , roleNotFoundException.getMessage())
        , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleUserNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.NOT_FOUND, userNotFoundException.getMessage())
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDto> handleBadCredentialsException(BadCredentialsException badCredentialsException){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.FORBIDDEN, "The username and/or password is incorrect, please try again.")
                , HttpStatus.FORBIDDEN);
    }
}
