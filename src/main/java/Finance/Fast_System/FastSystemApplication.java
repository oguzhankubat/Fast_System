package Finance.Fast_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@SpringBootApplication
@RestControllerAdvice
public class FastSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastSystemApplication.class, args);
	}
	
	
	@ExceptionHandler
	public Finance.Fast_System.Exceptions.ProblemDetails handleExceptionRuntime(RuntimeException runtimeException) {
		Finance.Fast_System.Exceptions.ProblemDetails problemDetails =new Finance.Fast_System.Exceptions.ProblemDetails();
		problemDetails.setMessage(runtimeException.getMessage());
		return problemDetails;
	}
	
	@ExceptionHandler
	public Finance.Fast_System.Exceptions.ProblemDetails handleExceptionEntity(EntityNotFoundException entityNotFoundException) {
		Finance.Fast_System.Exceptions.ProblemDetails problemDetails =new Finance.Fast_System.Exceptions.ProblemDetails();
		problemDetails.setMessage(entityNotFoundException.getMessage());
		return problemDetails;
	}
	@ExceptionHandler
	public Finance.Fast_System.Exceptions.ProblemDetails handleExceptionIllegal(IllegalArgumentException illegalArgumentException) {
		Finance.Fast_System.Exceptions.ProblemDetails problemDetails=new Finance.Fast_System.Exceptions.ProblemDetails();
		problemDetails.setMessage(illegalArgumentException.getMessage());
		
		return problemDetails;
	}
	
	@ExceptionHandler
	public Finance.Fast_System.Exceptions.ProblemDetails handleExceptionMethod(MethodArgumentNotValidException methodArgumentNotValidException) {
	    Finance.Fast_System.Exceptions.ProblemDetails problemDetails = new Finance.Fast_System.Exceptions.ProblemDetails();

	    StringBuilder errorMessageBuilder = new StringBuilder();
	    

	    methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError -> {
	        errorMessageBuilder.append(fieldError.getField())
	                .append(": ")
	                .append(fieldError.getDefaultMessage())
	                .append("; ");
	    });


	    if (errorMessageBuilder.length() == 0) {
	        errorMessageBuilder.append("Geçersiz giriş!");
	    }

	    problemDetails.setMessage(errorMessageBuilder.toString().trim());
	    
	    return problemDetails;
	}
}
