package com.turnitin.exercise.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.turnitin.exercise.domain.Student;
import com.turnitin.exercise.repository.StudentRepository;
import com.turnitin.exercise.exception.NoDataFoundException;
import com.turnitin.exercise.exception.StudentNotFoundException;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);

	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public Student createStudent(Student student) {
		if(student.getDisplayName()==null)
			student.setDisplayName(student.getFirstName()+" "+student.getLastName());
		
		student.setCreatedAt(LocalDateTime.now());
		student.setStartedAt(LocalDate.now());
		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		List<Student> studentList= new ArrayList<Student>();
		studentRepository.findAll().forEach(studentList::add);
		return studentList;
	}

	public Student getStudent(UUID id) {
		var student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
		return student;
	}

	public List<Student> retrieveStudentsBySearch(String name, String userName, String email, LocalDate startedDate) {
		var students = (List<Student>) studentRepository.findAll(new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(name!=null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("displayName"), "%"+name+"%")));
				}
				if(userName!=null){
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("userName"), "%"+userName+"%")));
				}
				if(email!=null){
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("email"), "%"+email+"%")));
				}
				if(startedDate!=null){
					Path<Date> dateStartedPath = root.get("startedAt");
					predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.<LocalDate>get("startedAt"), startedDate)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
		if( students.isEmpty()) {
			throw new NoDataFoundException();
		}
		return students;
	}
}
