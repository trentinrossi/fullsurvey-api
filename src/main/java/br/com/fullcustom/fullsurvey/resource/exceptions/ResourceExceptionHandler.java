package br.com.fullcustom.fullsurvey.resource.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fullcustom.fullsurvey.service.exceptions.BadRequestIdException;
import br.com.fullcustom.fullsurvey.service.exceptions.DataIntegrityException;
import br.com.fullcustom.fullsurvey.service.exceptions.ObjectNotFoundException;
import br.com.fullcustom.fullsurvey.service.exceptions.SequenceAlreadyExistsException;

@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Este método é o que vai ouvir e responder quando essa exceção ObjectNotFoundException foi chamada
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object not found", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
    /**
     * Este método é o que vai ouvir e responder quando essa exceção SequenceAlreadyExistsException foi chamada
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(SequenceAlreadyExistsException.class)
	public ResponseEntity<StandardError> sequenceAlreadyExistsException(SequenceAlreadyExistsException e, HttpServletRequest request) {		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Sequence already exists", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

    /**
     * Este método é o que vai ouvir e responder quando essa exceção DataIntegrityException foi chamada     
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Data integrity error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Data integrity violation error", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElementException(NoSuchElementException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "No value present with id", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

    /**
     * Será invocada quando ocorrer algum erro de validação das classes @Valid    
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

    /**
     * Será invocada quando ocorrer algum erro de validação das classes @Valid    
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> validation(ConstraintViolationException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Relational integrity error", e.getMessage(), request.getRequestURI());
        
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            err.addError(violation.getPropertyPath().toString(), violation.getMessage());
        }	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    /**
     * Será invocada quando ocorrer alguma divergência nos ID's das entidades sendo passapara para INSERT ou UPDATE 
     * Exemplo tirado do jHipster
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BadRequestIdException.class)
	public ResponseEntity<StandardError> badRequestId(BadRequestIdException e, HttpServletRequest request) {		
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "ID is mandatory", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> requestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {		
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Request method not supported", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {		
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Error to convert JSON value", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    

    // @ExceptionHandler(AuthorizationException.class)
	// public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		
	// 	StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
	// 	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	// }

    // @ExceptionHandler(FileException.class)
	// public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		
	// 	StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", e.getMessage(), request.getRequestURI());
	// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }

	// @ExceptionHandler(AmazonServiceException.class)
	// public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		
	// 	HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
	// 	StandardError err = new StandardError(System.currentTimeMillis(), code.value(), "Erro Amazon Service", e.getMessage(), request.getRequestURI());
	// 	return ResponseEntity.status(code).body(err);
	// }

	// @ExceptionHandler(AmazonClientException.class)
	// public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		
	// 	StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(), request.getRequestURI());
	// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }

	// @ExceptionHandler(AmazonS3Exception.class)
	// public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		
	// 	StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro S3", e.getMessage(), request.getRequestURI());
	// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	// }
}