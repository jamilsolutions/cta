package com.fs.cta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fs.cta.bom.Marketing;
import com.fs.cta.service.MarketingService;

@RestController
@RequestMapping("/marketing")
public class MarketingController {

	@Autowired
	private MarketingService marketingService;

	public MarketingController(MarketingService marketingService) {
		this.marketingService = marketingService;
	}

	@PostMapping("/save")
	public Marketing save(@Validated @RequestBody Marketing marketing) throws MethodArgumentNotValidException, Exception {
		if ( marketing == null ) {
			throw new MethodArgumentNotValidException(null, null);
		}
		marketing = this.marketingService.save(marketing);
		return marketing;
	}

	@GetMapping("/read/{id}")
	public Marketing read(@PathVariable Long id) {
		Optional<Marketing> marketing = this.marketingService.find(id);
		if (marketing.isPresent()) {
			return marketing.get();
		}
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
		String message = this.marketingService.delete(id);
		if ( message == null) {
		return ResponseEntity.status(HttpStatus.OK)
		        .body("{ \"message\": \"Marketing "+ id +" successfully deleted\" }");
		} else {
			return ResponseEntity.status(HttpStatus.OK)
			        .body("{ \"message\": \"" + message + "\" }");
		}
	}

	@GetMapping("/list")
	public List<Marketing> list() {
		List<Marketing> marketings = this.marketingService.list();
		return marketings; 
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, String> handleValidationExceptions(Exception ex) {
		Map<String, String> errors = new HashMap<String, String>();
		FieldError error = new FieldError("Exception", "Exception", ex.getMessage());
		String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
	    return errors;
	}

}
