package be.abis.menuapi.repository;

import be.abis.menuapi.model.SandwichCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SandwichCompanyJpaRepository extends JpaRepository<SandwichCompany, Integer> {

    @Query("select sc from SandwichCompany sc where sc.companyName = :companyName")
    SandwichCompany findCompany(@Param("companyName")String companyName);
}
