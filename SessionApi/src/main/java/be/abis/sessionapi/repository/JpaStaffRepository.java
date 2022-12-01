package be.abis.sessionapi.repository;

import be.abis.sessionapi.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

;

public interface JpaStaffRepository extends JpaRepository<Staff, Integer> {


    Staff findStaffByFirstNameAndLastName(String firstName, String lastName);
    Staff findStaffById(int Id);
}
