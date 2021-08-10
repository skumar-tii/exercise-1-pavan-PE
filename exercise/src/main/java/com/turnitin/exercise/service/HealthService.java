package com.turnitin.exercise.service;

import java.net.Socket;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.turnitin.exercise.domain.Student;

@Service
public class HealthService {
	
	@Value("${student.url}")
	private String studentUrl;
	private final RestTemplate rest = new RestTemplateBuilder().build();
	
	public Boolean healthCheck() throws Exception{
		Socket socket = 
    	        new Socket(new java.net.URL(studentUrl).getHost(),80);
		ResponseEntity<Student[]> responseType = rest.getForEntity(studentUrl, Student[].class);
		return true;
	}

}
