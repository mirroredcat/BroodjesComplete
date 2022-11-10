package be.abis.sessionapi;

import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.repository.StaffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FileStaffRepositoryTest {

    @Autowired
    StaffRepository staffRepository;

    @Test
    public void staffRepositoryGetsCreatedTest() {
        assertEquals(4, staffRepository.getAllStaff().size());
    }

    @Test
    public void findTeacherByNameWoutersTest() throws PersonNotFoundException {
        assertEquals("Wouter", staffRepository.findTeacherByName("Wouter", "Wouters").getFirstName());
    }

    @Test
    public void findTeacherByNameErrorTest() {
        assertThrows(PersonNotFoundException.class, ()-> staffRepository.findTeacherByName("sfefsefs", "fqzfqzfqz"));
    }

    @Test
    public void findTeacherById2ShouldBeJosTest() throws PersonNotFoundException {
        assertEquals("Jos", staffRepository.findTeacherById(2).getFirstName());
    }

    @Test
    public void findTeacherById6ShouldThrowError(){
        assertThrows(PersonNotFoundException.class, ()-> staffRepository.findTeacherById(99));
    }

}
