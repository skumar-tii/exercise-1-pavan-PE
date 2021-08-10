package com.turnitin.exercise.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "userName cannot be blank")
	private String userName;
	
	private String firstName;
	@NotBlank(message = "lastName cannot be blank")
	private String lastName;
	private String displayName;
	private LocalDateTime createdAt;
	private LocalDate startedAt;
	
	public Student() {
		super();
	}
	
	public Student(UUID id, @Email(message = "Email should be valid") String email,
			@NotBlank(message = "userName cannot be blank") String userName, String firstName,
			@NotBlank(message = "lastName cannot be blank") String lastName, String displayName,
			LocalDateTime createdAt, LocalDate startedAt) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.displayName = displayName;
		this.createdAt = createdAt;
		this.startedAt = startedAt;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(LocalDate startedAt) {
		this.startedAt = startedAt;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", email=" + email + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", displayName=" + displayName + ", createdAt=" + createdAt
				+ ", startedAt=" + startedAt + "]";
	}
}
