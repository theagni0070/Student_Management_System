package com.student.service;
import org.springframework.stereotype.Service;

import com.student.exception.StudentException;
import com.student.model.Address;
import com.student.model.Course;
import com.student.model.Student;
import java.util.List;

@Service
public interface StudentService {

    public Student registerStudent(Student studentDTO);

    public Student getStudentById(Integer studentId) throws StudentException;

    public List<Student> getStudentByName(String name) throws StudentException;

    public List<Course> getAllCoursesAdminPurpose(Integer studentId)throws StudentException;

    public Student updateEmailAndMobile(Integer studentId,String email,String mobile)throws StudentException;

    public Student updateStudentAddress(Integer studentId,Address address)throws StudentException;

    public List<Course> getStudentCourses(Integer studentId)throws StudentException;

    public Course leaveCourse(Integer studentId,Integer courseId)throws StudentException;

    public Student addNewAddress(Address address,Integer studentId)throws StudentException;

}
