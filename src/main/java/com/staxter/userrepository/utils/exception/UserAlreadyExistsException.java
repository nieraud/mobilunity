package com.staxter.userrepository.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="USER_ALREADY_EXISTS")
public class UserAlreadyExistsException extends Exception {}
