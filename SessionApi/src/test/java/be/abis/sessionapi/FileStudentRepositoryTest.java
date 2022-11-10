package be.abis.sessionapi;


import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FileStudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void studentListGetsCreatedTest() {
        assertEquals(13, studentRepository.findAllStudents().size());
    }

    @Test
    public void findStudentByID6ShouldBeSteve() throws PersonNotFoundException {
        assertEquals("Steve", studentRepository.findStudentById(6).getFirstName());
    }

    @Test
    public void findStudentByIDShouldThrowPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class,()-> studentRepository.findStudentById(200));
    }

    @Test
    public void findStudentByID6to9ShouldReturnListOf4() {
        assertEquals(4, studentRepository.findStudentsByIds(6,7,8,9).size());
    }
    @Test
    public void FindStudentListByID3To9ShouldReturnSteveFor6() {
        assertEquals("Steve", studentRepository.findStudentsByIds(3,4,5,6,7,8,9).get(3).getFirstName());
    }
    @Test
    public void findStudentByNameSteveShouldBe5() throws PersonNotFoundException {
        assertEquals(6,studentRepository.findStudent("Steve", "Sigmundson").getId());
    }






}
