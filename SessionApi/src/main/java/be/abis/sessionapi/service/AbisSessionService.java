package be.abis.sessionapi.service;


import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;
import be.abis.sessionapi.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisSessionService implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public AbisSessionService() {
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAllSessions();
    }

    @Override
    public List<Session> getTodaysSessions() throws SessionNotFoundException {
        return sessionRepository.findSessionsOfToday();

    }
}
