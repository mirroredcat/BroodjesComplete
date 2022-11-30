package be.abis.sessionapi;


import be.abis.sessionapi.dto.SessionDTO;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import be.abis.sessionapi.repository.JpaCourseRepository;
import be.abis.sessionapi.repository.JpaSessionRepository;
import be.abis.sessionapi.service.SessionService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Autowired
    JpaSessionRepository jpaSessionRepository;

    @Autowired
    JpaCourseRepository jpaCourseRepository;


    @Test
    public void findAllSessionsTest() {

        List<Session> foundSessions = sessionService.getAllSessions();
        assertEquals(7, sessionService.getAllSessions().size());
    }

    @Test
    @Transactional
    public void getSessionsOfTodayTest() throws SessionNotFoundException {
        Session newSession = new Session();
        newSession.setStartDate(LocalDate.now());
        newSession.setCourse(jpaCourseRepository.findCourseById(2));
        jpaSessionRepository.save(newSession);
/*        List<SessionDTO> foundSessions = sessionService.getTodaysSessions();
        System.out.println(foundSessions.size());
        for (SessionDTO session : foundSessions) {
            System.out.println(session.getCourse().getTeacher().getFirstName());
            System.out.println(session.getStudentList().size());
        }*/
    }

    @Test
    @Transactional
    public void getSessionsOfTodayNoSessionsTest() {
        jpaSessionRepository.deleteById(7);
        assertThrows(SessionNotFoundException.class,()->sessionService.getTodaysSessions());

    }







}
