package be.abis.sessionapi.repository;



import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.model.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> findAllSessions();
    List<Session> findSessionsOfToday() throws SessionNotFoundException;
}
