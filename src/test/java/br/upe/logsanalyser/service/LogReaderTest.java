package br.upe.logsanalyser.service;

import br.upe.logsanalyser.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogReaderTest {

    @Test
    void testLerLogs() throws Exception {
        String caminho = "test-log.txt";
        FileWriter writer = new FileWriter(caminho);
        writer.write("123.45.67.89 - - [10/Oct/2021:13:55:36 -0300] \"GET /home HTTP/1.1\" 200 1234 \"-\" \"Mozilla/5.0 (Windows NT 10.0)\"\n");
        writer.close();

        LogReader reader = new LogReader();
        List<LogEntry> logs = reader.lerLogs(caminho);

        assertEquals(1, logs.size());
        assertEquals("123.45.67.89", logs.get(0).getIp());
    }
}
