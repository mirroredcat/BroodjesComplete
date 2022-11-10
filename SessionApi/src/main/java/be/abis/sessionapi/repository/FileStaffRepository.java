package be.abis.sessionapi.repository;


import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.model.Person;
import be.abis.sessionapi.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileStaffRepository implements StaffRepository{

    private List<Person> allStaff  = new ArrayList<>();

    private String fileLocation = "/temp/javacourses/Broodjes/staff.csv";

    @PostConstruct
    public void init() {
        this.readFile();
    }

    public FileStaffRepository() {
    }

    public void readFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String line : lines) {
            Person p =  convertToTeacherObj(line);
            allStaff.add(p);
        }
    }

    @Override
    public Person findTeacherByName(String firstName, String lastName) throws PersonNotFoundException {
        Person foundPerson = allStaff.stream()
                .filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Person " + firstName + " " + lastName + " not found"));
        return foundPerson;
    }



    @Override
    public Teacher findTeacherById(int id) throws PersonNotFoundException{
        Person foundPerson =  allStaff.stream()
                .filter(p -> p.getId() == id)
                .findFirst().orElseThrow(() -> new PersonNotFoundException("Person with id " + id + " was not found."));
        Teacher foundTeacher = new Teacher();
        foundTeacher.setFirstName(foundPerson.getFirstName());
        foundTeacher.setLastName(foundPerson.getLastName());
        foundTeacher.setId(foundPerson.getId());
        return foundTeacher;
    }

    @Override
    public Teacher convertToTeacherObj(String attr){
        String[] vals = attr.split(";");
        if(!vals[0].equals("")){
            Teacher p = new Teacher();
            p.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
            p.setFirstName(!vals[1].equals("null")? vals[1] : null );
            p.setLastName(!vals[2].equals("null") ? vals[2] : null);
            return p;
        }
        return null;
    }

    @Override
    public List<Person> getAllStaff() {
        return allStaff;
    }
}
