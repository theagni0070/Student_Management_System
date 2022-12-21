package com.student.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.student.exception.StudentException;
import com.student.model.Student;
import com.student.service.StudentService;

@RestController
public class Studentcontroller {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students/")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){

        Student student1 = studentService.registerStudent(student);

        return new ResponseEntity<Student>(student1,HttpStatus.CREATED);

    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentByID(@PathVariable Integer studentId) throws StudentException{

        Student student = studentService.getStudentById(studentId);

        return new ResponseEntity<Student>(student,HttpStatus.ACCEPTED);

    }

    @GetMapping("/students/{name}")
    public ResponseEntity<List<Student>> getByName(@PathVariable String name) throws StudentException{

        List<Student> list = studentService.getStudentByName(name);

        return new ResponseEntity<List<Student>>(list,HttpStatus.ACCEPTED);

    }

}
