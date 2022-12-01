package be.abis.superdupersandwichorder.repository;

import be.abis.superdupersandwichorder.model.StoredDayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface JpaOrderRepository extends JpaRepository<StoredDayOrder, Integer> {




    @Modifying
    @Query(value = "insert into orders (odate, o_seid, osize, o_scid, ototal) values(:date, :sessionid, :ordersize, :companyid, :totalprice)", nativeQuery = true)
    void addDayOrder(@Param("date") LocalDate date,  @Param("sessionid") int sessionid,
                     @Param("ordersize") int ordersize, @Param("companyid") int companyid, @Param("totalprice") double totalprice);

    StoredDayOrder findStoredDayOrderByDate(LocalDate date);

    @Query(value = "select * from orders where o_seid=:sessionid", nativeQuery = true)
    List<StoredDayOrder> findAllOrdersBySessionId(@Param("sessionid") int sessionId);



}

