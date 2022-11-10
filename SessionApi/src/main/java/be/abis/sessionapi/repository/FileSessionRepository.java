package be.abis.sessionapi.repository;

import be.abis.sessionapi.exceptions.CourseNotFoundException;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@ConditionalOnBean(value = CourseRepository.class)
public class FileSessionRepository implements SessionRepository {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;


    private List<Session> sessionList = new ArrayList<>();
    private String fileLocation = "/temp/javacourses/Broodjes/sessions.csv";

    @PostConstruct
    public void init() throws CourseNotFoundException {
        this.readFile();
    }


    public FileSessionRepository() {
    }

    public void readFile() throws CourseNotFoundException {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileLocation));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String s : lines) {
            String[] vals = s.split(";");
            if (!vals[0].equals("")) {
                Session session = new Session();
                session.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]) : null);
                session.setCourse(!vals[2].equals("null") ? courseRepository.findCourseByID(Integer.parseInt(vals[2])) : null);
                session.setTeacher(!vals[2].equals("null") ? courseRepository.findCourseByID(Integer.parseInt(vals[2])).getTeacher() : null);
                session.setStudentList(!vals[3].equals("null") ? studentRepository.findStudentsByIds(stringToIntegerArray(vals[3])) : null);
                session.setDates(!vals[1].equals("null") ? convertToDatesListObj(vals[1]) : null);
                sessionList.add(session);
            }
        }
    }

    public List<LocalDate> convertToDatesListObj(String attr) {
        String[] vals = attr.split(",");
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<LocalDate> dateList = new ArrayList<>();
        for (String d : vals) {
            dateList.add(LocalDate.parse(d, dtm));
        }

        return dateList;
    }

    @Override
    public List<Session> findAllSessions() {
        return this.sessionList;
    }

    @Override
    public List<Session> findSessionsOfToday() throws SessionNotFoundException {
        List<Session> foundSessions = sessionList.stream()
                .filter(session -> session.getDates().contains(LocalDate.now()))
                .collect(Collectors.toList());

        if (foundSessions.isEmpty()) {
            throw new SessionNotFoundException("No sessions found");
        }

        return foundSessions;
    }

    int[] stringToIntegerArray(String integerLine) {
        String[] vals = integerLine.split(",");
        int size = vals.length;
        int[] arr = new int[size];
        for (int i=0; i<size;i++) {
            arr[i] = Integer.parseInt(vals[i]);
        }
        return arr;
    }







}
