package com.student.service;

import com.student.model.Course;
import com.student.model.Student;

public interface CourseService {
    
    public boolean addCourse(Course course);

    public Student assignCourseToStudent(Integer studentId,Integer courseId);

    public Student getStudentsFromCourse(Integer courseId);

    public Course removeCourse(Integer courseId);

}
