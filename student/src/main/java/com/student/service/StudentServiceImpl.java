package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exception.StudentException;
import com.student.model.Address;
import com.student.model.Course;
import com.student.model.Student;
import com.student.repository.AddressDao;
import com.student.repository.CourseDao;
import com.student.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao sDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private AddressDao addressDao;

    @Override
    public Student registerStudent(Student student) {

        return sDao.save(student);
    }

    @Override
    public Student getStudentById(Integer studentId) throws StudentException {

        return sDao.findById(studentId).orElseThrow(() -> new StudentException("Invalid StudentId "+studentId));

    }

    @Override
    public List<Student> getStudentByName(String name) throws StudentException{

        List<Student> list = sDao.getStudentsByName(name);

        if(list.isEmpty())
            throw new StudentException("No student found with name : "+name);

        return list;
    }

    @Override
    public List<Course> getAllCoursesAdminPurpose(Integer studentId) throws StudentException {

        return sDao.findById(studentId).orElseThrow(() -> new StudentException("Invalid StudentId "+ studentId)).getCourseList();

    }

    @Override
    public Student updateEmailAndMobile(Integer studentId,String email,String mobile) throws StudentException{

        Optional<Student> student = sDao.findById(studentId);

        if(student.isPresent()){

            Student data = student.get();
            data.setEmail(email);
            data.setMobileNo(mobile);
            return sDao.save(data);

        }

        throw new StudentException("Invalid Student Id "+studentId);

    }

    @Override
    public Student updateStudentAddress(Integer studentId,Address address) throws StudentException{

        Optional<Student> student = sDao.findById(studentId);

        if(student.isEmpty()) throw new StudentException("Invalid student id "+studentId);

        List<Address> list = student.get().getStudentAddress();

        for(Address add:list){
            if(add.getAddressId().equals(address.getAddressId())){
                addressDao.save(address);
            }
        }



        return sDao.findById(studentId).get();
    }

    @Override
    public List<Course> getStudentCourses(Integer studentId) throws StudentException{

        return sDao.findById(studentId).orElseThrow(() -> new StudentException("Invalid Student Id "+studentId)).getCourseList();

    }

    @Override
    public Course leaveCourse(Integer studentId, Integer courseId) throws StudentException{

        Optional<Student> student = sDao.findById(studentId);

        Optional<Course>  course = courseDao.findById(courseId);

        if(student.isEmpty() || course.isEmpty()) throw new StudentException("Invalid credentials");

        List<Course> list1 = student.get().getCourseList();

        List<Student> list2 = course.get().getStudentList();

        for(Course c:list1){
            if(c.getCourseId().equals(courseId)){
                list1.remove(c);
            }
        }

        for(Student s:list2){
            if(s.getStudentCode().equals(studentId)){
                list2.remove(s);
            }
        }

        Course data1 =  course.get();

        data1.setStudentList(list2);

        Student data2 = student.get();

        data2.setCourseList(list1);

        courseDao.save(data1);

        sDao.save(data2);

        return data1;
    }

    @Override
    public Student addNewAddress(Address address, Integer studentId) throws StudentException{

        Optional<Student> sOptional = sDao.findById(studentId);

        if(sOptional.isEmpty()) throw new StudentException("Invalid studentId");

        Address ad = addressDao.save(address);

        Student data = sOptional.get();
        
        List<Address> list = data.getStudentAddress();
        
        list.add(ad);

        data.setStudentAddress(list);

        return sDao.save(data);
    }
    
}
