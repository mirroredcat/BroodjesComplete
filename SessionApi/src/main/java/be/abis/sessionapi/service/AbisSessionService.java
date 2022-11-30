package be.abis.sessionapi.service;


import be.abis.sessionapi.dto.SessionDTO;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import be.abis.sessionapi.model.Student;
import be.abis.sessionapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AbisSessionService implements SessionService {

    @Autowired
    JpaSessionRepository jpaSessionRepository;
    @Autowired
    JpaStaffRepository jpaStaffRepository;
    @Autowired
    JpaStudentRepository jpaStudentRepository;
    @Autowired
    JpaCourseRepository jpaCourseRepository;

    public AbisSessionService() {
    }

    @Override
    public List<Session> getAllSessions() {

        return jpaSessionRepository.findAll();

    }

    @Override
    public List<SessionDTO> getTodaysSessions() throws SessionNotFoundException {
        List<Session> foundSessions = null;
        foundSessions = jpaSessionRepository.findSessionsOfToday();

        try {
            foundSessions.get(0);
        } catch (RuntimeException e) {
            throw new SessionNotFoundException("No sessions found");
        }

        List<SessionDTO> foundSessionDTOList = new ArrayList<>();

        for (Session session : foundSessions) {
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setCourse(session.getCourse());
            sessionDTO.setSessionId(session.getId());
        //    sessionDTO.setTeacher(session.getCourse().getTeacher());

            List<Integer> studentIdList = session.getEnrollmentIds();

            List<Integer> filteredStudentIdList = studentIdList.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            List<Student> studentList = new ArrayList<>();

            for (int foundStudent : filteredStudentIdList) {
               studentList.add(jpaStudentRepository.findStudentById(foundStudent));
            }
            sessionDTO.setStudentList(studentList);
            foundSessionDTOList.add(sessionDTO);

        }

        return foundSessionDTOList;
    }
}
