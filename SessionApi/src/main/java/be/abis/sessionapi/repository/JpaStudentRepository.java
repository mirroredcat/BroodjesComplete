package be.abis.sessionapi.repository;


import be.abis.sessionapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentByFirstNameAndLastName(String firstName, String lastName);
    Student findStudentById(int id);
    List<Student> findStudentsById(int... id);
}
