package be.abis.menuapi.repository;



import be.abis.menuapi.exceptions.SandwichNotFoundException;
import be.abis.menuapi.model.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SandwichJpaRepository extends JpaRepository<Sandwich, Integer> {


    @Query(value="select * from sandwiches join sand_comps sc on sandwiches.s_scid = sc.scid where scname = :companyName ", nativeQuery = true)
    public List<Sandwich> findSandwichesByRestaurant(@Param("companyName")String companyName);

    @Query(value="select * from sandwiches join sand_comps sc on sc.scid = sandwiches.s_scid where sname = :sandwichName and scname = :companyName", nativeQuery = true)
    public Sandwich findSandwich(@Param("sandwichName")String sandwichName, @Param("companyName")String companyName);

    @Query("select s from Sandwich s where s.id = :id")
    Sandwich findSandwich(@Param("id")Integer id);

    @Modifying
    @Query(value = "update sandwiches set sprice = :newPrice where said = :id", nativeQuery = true)
    void updateSandwichPrice(@Param("id")int id, @Param("newPrice")Double newPrice);

    @Modifying
    @Query(value="update sandwiches set singred = :newIngredients where said = :id", nativeQuery = true)
    void updateSandwichIngredients(@Param("id")int id, @Param("newIngredients")String newIngredients) ;
}
