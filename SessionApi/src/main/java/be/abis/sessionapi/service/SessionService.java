package be.abis.sessionapi.service;



import be.abis.sessionapi.dto.SessionDTO;
import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;

import java.util.List;

public interface SessionService {

    List<Session> getAllSessions();
    List<SessionDTO> getTodaysSessions() throws SessionNotFoundException;
}
