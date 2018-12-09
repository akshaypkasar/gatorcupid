package com.gatorcupid.server.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse();
	  return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(GCException.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(GCException ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getCode(), ex.getErrorMessage());
	  logger.error(ex);
	  return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}