package com.turnitin.exercise.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.turnitin.exercise.domain.Student;
import com.turnitin.exercise.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Student getStudent(@PathVariable UUID id) {
		return studentService.getStudent(id);
	}
	
	@GetMapping
	@ResponseBody
	public List<Student> retrieveStudentsBasedOnSearch(  @RequestParam(name = "name",required = false) String name,
													@RequestParam(name = "username",required = false) String userName,
													@RequestParam(name = "email",required = false) String email,
													@RequestParam(name = "started_after",required = false) 
													@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startedDate ) {
		return studentService.retrieveStudentsBySearch(name, userName, email, startedDate);
	}

}
