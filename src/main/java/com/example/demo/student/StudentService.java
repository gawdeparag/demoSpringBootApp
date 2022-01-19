package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email Already exists");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("Student with ID " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, Student updatedStudent) {
		Student student = studentRepository.findById(studentId)
							.orElseThrow(()-> new IllegalStateException("Student with ID " + studentId + " does not exist"));
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(updatedStudent.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email Already exists");
		}
		if(updatedStudent.getName() != null && updatedStudent.getName().length() > 0) {
			student.setName(updatedStudent.getName());
		}
		if(updatedStudent.getEmail() != null && updatedStudent.getEmail().length() > 0) {
			student.setEmail(updatedStudent.getEmail());
		}
		if(updatedStudent.getDob() != null) {
			student.setDob(updatedStudent.getDob());
		}
	}
}
