package be.abis.sessionapi.repository;



import be.abis.sessionapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaSessionRepository extends JpaRepository<Session, Integer> {


    @Query(value = "select * from sessions where seid in (select seid from sessions join courses on se_cid=cid where current_date between sestart and (sestart + cduration))", nativeQuery = true)
    List<Session> findSessionsOfToday();
}
