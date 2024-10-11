package iitu.edu.kz.aruka.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class Student {

	@NonNull
	private int registrationId;
	
	@NotEmpty
	private String name;
	
	@Past
	private LocalDate dateOfBirth;
	private String gender;

	@NotEmpty
	private String enrolledIn;

	@Email
	private String email;

	@Size(min = 5, max = 10)
	@NotNull
	private String phoneNumber;
	private List<String> interests;

	public Student(int registrationId, String name, LocalDate dateOfBirth, String gender, String enrolledIn,
			String email, String phoneNumber, List<String> interests) {
		super();
		this.registrationId = registrationId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.enrolledIn = enrolledIn;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.interests = interests;
	}

	public Student() {

	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEnrolledIn() {
		return enrolledIn;
	}

	public void setEnrolledIn(String enrolledIn) {
		this.enrolledIn = enrolledIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

}
