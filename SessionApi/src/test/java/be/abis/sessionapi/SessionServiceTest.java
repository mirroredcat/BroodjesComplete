package be.abis.sessionapi;


import be.abis.sessionapi.exceptions.SessionNotFoundException;
import be.abis.sessionapi.service.SessionService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionService sessionService;


    @Test
    public void findSessionOfTodayTest() throws SessionNotFoundException {
        LocalDate currentLocalDate = LocalDate.of(2022,10,25);
        try (MockedStatic<LocalDate> mock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)){
            mock.when(()->LocalDate.now()).thenReturn(currentLocalDate);
            assertEquals(2, sessionService.getTodaysSessions().size());
        }
    }

    @Test
    public void findSessionOfTodayThrowsExceptionTest() {
        LocalDate currentLocalDate = LocalDate.of(2032, 10, 25);
        try (MockedStatic<LocalDate> mock = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
            mock.when(() -> LocalDate.now()).thenReturn(currentLocalDate);
            assertThrows(SessionNotFoundException.class, () -> sessionService.getTodaysSessions().size());
        }

    }





}
