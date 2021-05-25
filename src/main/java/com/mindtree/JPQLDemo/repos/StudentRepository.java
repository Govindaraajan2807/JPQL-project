package com.mindtree.JPQLDemo.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mindtree.JPQLDemo.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

	@Query("from Student")
	Set<Student>findAllStudents();
	
	@Query("select firstName,score from Student")
	List<Object[]>findAllStudentPartialData();
	
	@Query("from Student where firstName =:firstName")
	List<Student>findAllStudentsByFirstName(@Param("firstName")String firstName);
	
	@Query("from Student where score>:min and score<:max")
	List<Student>findAllStudentsByScore(@Param("min")int min,@Param("max")int max);
	
	@Modifying
	@Query("delete from Student where firstName = :firstName")
	void deleteStudentsByFirstName(@Param("firstName")String firstName);
	
	@Query("from Student")
	List<Student>findAllStudentByPaging(Pageable Page);
	
	@Query(value = "select * from student",nativeQuery = true)
	List<Student>findAllStudentsNQ();
	
	@Query(value = "select * from student where fname =:firstName",nativeQuery = true)
	List<Student>findAllStudentsByFirstNameNQ(@Param("firstName")String firstName);
	
}
