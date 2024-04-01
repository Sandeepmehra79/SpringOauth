package com.userService.demo.exception;

import com.userService.demo.model.Role;

public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(String message){
        super(message);
    }
}
