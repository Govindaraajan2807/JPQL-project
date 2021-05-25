package com.mindtree.JPQLDemo;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;

import com.mindtree.JPQLDemo.entities.Student;
import com.mindtree.JPQLDemo.repos.StudentRepository;

@SpringBootTest
class JpqlApplicationTests {
	@Autowired
	StudentRepository repos;

	@Test
	public void testStudentCreate() {
		Student s = new Student();
		s.setLname("Govind");
		s.setFirstName("L");
		s.setScore(75);
		repos.save(s);
	}// By default, Hibernate generates key from hibernate_sequence table, we can
		// disable it by setting this hibernate.use-new-id-generator-mappings to false.

	@Test
	public void findAllStudents() {
		Set<Student> student = repos.findAllStudents();
		student.forEach(
				p -> System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getId() + " " + p.getScore()));
	}// read all the data

	@Test
	public void findAllStudentPartialData() {
		List<Object[]> student = repos.findAllStudentPartialData();
		for (Object[] objects : student) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}

	@Test
	public void findAllStudentsByFirstName() {
		List<Student> student = repos.findAllStudentsByFirstName("Govind");
		student.forEach(p -> System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getScore()));
	}

	@Test
	public void findAllStudentsByScore() {
		List<Student> student = repos.findAllStudentsByScore(60, 70);
		student.forEach(p -> System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getId()));
	}

	// while performing delete operations we should use @modifying @transactional
	// @rollback otherwise we will get error

	@Test
	@Transactional
	@Rollback
	public void deleteStudentsByFirstName() {
		repos.deleteStudentsByFirstName("Govind");
	}

	@Test
	public void findAllStudentByPaging() {
		Pageable pageable = PageRequest.of(0, 2, Direction.ASC, "fname");
		List<Student> student = repos.findAllStudentByPaging(pageable);
		student.forEach(
				p -> System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getId() + " " + p.getScore()));
	}
	
	@Test 
	public void findAllStudentsNQ() {
		List<Student> student = repos.findAllStudentsNQ();
		student.forEach(p->System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getScore()));
	}
	
	@Test
	public void findAllStudentsByFirstNameNQ() {
		List<Student>student = repos.findAllStudentsByFirstNameNQ("Govind");
		student.forEach(p->System.out.println(p.getFirstName() + " " + p.getLname() + " " + p.getScore()));
	}
}
