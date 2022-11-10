package be.abis.sessionapi.repository;



import be.abis.sessionapi.exceptions.PersonNotFoundException;
import be.abis.sessionapi.model.Person;
import be.abis.sessionapi.model.Teacher;

import java.util.List;

public interface StaffRepository {


     Person findTeacherByName(String firstName, String lastName) throws PersonNotFoundException;
     Teacher findTeacherById(int id) throws PersonNotFoundException;
     Teacher convertToTeacherObj(String attr);
     List<Person> getAllStaff();
}
