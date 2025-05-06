package br.upe.logsanalyser.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogEntryTest {

    @Test
    void testFoiRespondidaComSucesso() {
        LogEntry log = new LogEntry("127.0.0.1", "01/Jan/2021", "GET /index.html", 200, 1024, "Mozilla");
        assertTrue(log.foiRespondidaComSucesso());
    }

    @Test
    void testIsPost() {
        LogEntry log = new LogEntry("127.0.0.1", "01/Jan/2021", "POST /form", 201, 500, "Mozilla");
        assertTrue(log.isPost());
    }

    @Test
    void testDetectarSistemaOperacionalWindows() {
        LogEntry log = new LogEntry("127.0.0.1", "01/Jan/2021", "GET /", 200, 100, "Mozilla/5.0 (Windows NT 10.0)");
        assertEquals("Windows", log.detectarSistemaOperacional());
    }
}
