package be.abis.sessionapi.repository;



import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.model.Student;

import java.util.List;

public interface StudentRepository {

    Student findStudent(String firstName, String lastName) throws PersonNotFoundException;
    Student findStudentById(int id) throws PersonNotFoundException;
    List<Student> findAllStudents();
    List<Student> findStudentsByIds(int... ids);
    Student convertToStudentObj(String attr);


}
