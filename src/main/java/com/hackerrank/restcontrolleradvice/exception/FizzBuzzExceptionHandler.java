package com.hackerrank.restcontrolleradvice.exception;

import com.hackerrank.restcontrolleradvice.dto.BuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzBuzzException;
import com.hackerrank.restcontrolleradvice.dto.FizzException;
import com.hackerrank.restcontrolleradvice.dto.GlobalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class FizzBuzzExceptionHandler extends ResponseEntityExceptionHandler {

  //TODO:: implement hander methods for FizzException, BuzzException and FizzBuzzException
    @ExceptionHandler(FizzException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected GlobalError handleFizzException(FizzException ex, WebRequest request){
        GlobalError error = new GlobalError("Fizz Exception has been thrown",
                "Internal Server Error");
        return error;
    }
    @ExceptionHandler(BuzzException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected GlobalError handlebuzzException(BuzzException ex, WebRequest request){
        GlobalError error = new GlobalError("Buzz Exception has been thrown",
                "Bad Request");
        return error;
    }
    @ExceptionHandler(FizzBuzzException.class)
    @ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
    protected GlobalError handlefizzbuzzException(FizzBuzzException ex, WebRequest request){
        GlobalError error = new GlobalError("FizzBuzz Exception has been thrown",
                "Insufficient Storage");
        return error;
    }

}
