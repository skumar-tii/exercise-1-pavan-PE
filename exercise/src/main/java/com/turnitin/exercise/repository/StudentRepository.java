package com.turnitin.exercise.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.turnitin.exercise.domain.Student;

public interface StudentRepository extends JpaRepository<Student, UUID>,JpaSpecificationExecutor<Student>{

}
