package com.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rest.entity.Student;
import com.rest.repository.StudentRepository;
import com.rest.request.CreateStudentRequest;
import com.rest.response.StudentResponse;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;

	@Autowired
	CommonService commonService;
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
		return studentResponse;
	}
	
	public StudentResponse getById (long id) {
		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
		return studentResponse;
	}
}
