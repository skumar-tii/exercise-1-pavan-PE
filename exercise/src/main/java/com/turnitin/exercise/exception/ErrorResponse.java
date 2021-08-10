package com.turnitin.exercise.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
  private int status;
  private String message;
  private String stackTrace;
  private List<ValidationError> errors;
  
  public ErrorResponse(int status, String message, String stackTrace, List<ValidationError> errors) {
	super();
	this.status = status;
	this.message = message;
	this.stackTrace = stackTrace;
	this.errors = errors;
  }

  public ErrorResponse(int status, String message) {
	  super();
	  this.status = status;
	  this.message = message;

  }

private static class ValidationError {
    public ValidationError(String field, String message) {
    	super();
    	this.field = field;
    	this.message = message;
	}
	private String field;
    private String message;
    
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
  }

  public void addValidationError(String field, String message){
    if(Objects.isNull(errors)){
      errors = new ArrayList<>();
    }
    errors.add(new ValidationError(field, message));
  }
}

