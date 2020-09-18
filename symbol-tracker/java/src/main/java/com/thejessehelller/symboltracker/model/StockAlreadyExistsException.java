package com.thejessehelller.symboltracker.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Stock already being tracked.")
public class StockAlreadyExistsException extends RuntimeException{
}
