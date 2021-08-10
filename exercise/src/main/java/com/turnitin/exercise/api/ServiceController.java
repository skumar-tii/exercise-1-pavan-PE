package com.turnitin.exercise.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.turnitin.exercise.service.HealthService;

@RestController
@RequestMapping("/service")
public class ServiceController {
	private final HealthService healthService;

	public ServiceController(HealthService healthService) {
		super();
		this.healthService = healthService;
	}

	@GetMapping("/health")
	public ResponseEntity<HttpStatus> healthCheck() {
		try {
			if(healthService.healthCheck())
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
