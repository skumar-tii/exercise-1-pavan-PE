package com.turnitin.exercise;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.turnitin.exercise.api.StudentController;
import com.turnitin.exercise.domain.Student;
import com.turnitin.exercise.service.StudentService;

@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StudentService studentService;

	Student mockStudent1 = new Student(UUID.randomUUID(), "frank@example.com", "frankie",
			"Frank", "Edwards", "Frnak Edwards",LocalDateTime.now(), LocalDate.now());


	@Test
	public void retrieveStudentsBasedOnSearch() throws Exception {
		UUID uuid= UUID.randomUUID();
		Student mockStudent = new Student(uuid, "test123@yahoo.com", "Rocky123",
				"", "Rocky", "Rocky",LocalDateTime.now(), LocalDate.now());
		List<Student> mockStudentList = new ArrayList<Student>();
		mockStudentList.add(mockStudent);

		Mockito.when(
				studentService.retrieveStudentsBySearch(Mockito.any(String.class),
						Mockito.any(String.class),Mockito.any(String.class),Mockito.any(LocalDate.class))).thenReturn(mockStudentList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/students")
				.param("name", "Rock")
				.param("username", "Rock")
				.param("email", "Rock")
				.param("started_after", "2021-08-08")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expected = "[{\"email\":\"test123@yahoo.com\",\"userName\":\"Rocky123\",\"lastName\":\"Rocky\"}]";

		JSONAssert.assertEquals(expected, response
				.getContentAsString(), false);
	}

	@Test
	public void createStudent() throws Exception {

		Mockito.when(
				studentService.createStudent(
						Mockito.any(Student.class))).thenReturn(mockStudent1);

		String exampleStudentJson = "{\"email\":\"matt123@gmail.com\",\"userName\":\"matt123\",\"firstName\":\"Matt\",\"lastName\":\" Thomas\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/students")
				.accept(MediaType.APPLICATION_JSON).content(exampleStudentJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void getStudent() throws Exception {
		UUID uuid= UUID.randomUUID();
		Student mockStudent = new Student(uuid, "pete456@gmail.com", "Pete456",
				"Mathew", "Peter", "Mathew Pater",LocalDateTime.now(), LocalDate.now());

		Mockito.when(
				studentService.getStudent(Mockito.any(UUID.class))).thenReturn(mockStudent);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/students/"+uuid)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expected = "{\"email\":\"pete456@gmail.com\",\"userName\":\"Pete456\",\"lastName\":\"Peter\",\"firstName\":\"Mathew\"}";

		JSONAssert.assertEquals(expected, response
				.getContentAsString(), false);
	}

}