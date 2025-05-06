package br.upe.logsanalyser.service;

import br.upe.logsanalyser.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

class LogAnalyserTest {

    @Test
    void testMostrarMediaPOST2021() {
        List<LogEntry> logs = List.of(
                new LogEntry("1.1.1.1", "01/Jan/2021:12:00:00 -0300", "POST /api", 200, 1000, "Mozilla"),
                new LogEntry("2.2.2.2", "01/Jan/2021:12:00:00 -0300", "POST /api", 200, 500, "Mozilla")
        );

        LogAnalyser analyser = new LogAnalyser(logs);

    }

}
