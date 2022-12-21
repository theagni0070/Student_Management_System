package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.student.model.Student;
@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {
    
    @Query("from Student s where s.studentName LIKE %:name% ") 
	public List<Student> getStudentsByName(String name) ;

}
