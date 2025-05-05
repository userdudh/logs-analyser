package br.upe.logsanalyser.service;

import br.upe.logsanalyser.model.LogEntry;

import java.io.*;
import java.util.*;

public class LogReader {

    public List<LogEntry> lerLogs(String caminhoArquivo) {
        List<LogEntry> logs = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\"");

                if (partes.length >= 3) {
                    String[] dadosIniciais = partes[0].trim().split(" ");
                    String ip = dadosIniciais[0];

                    // Captura data e hora entre colchetes (ex: [10/Oct/2023:13:55:36 -0300])
                    String dataHora = linha.substring(linha.indexOf('[') + 1, linha.indexOf(']'));

                    String requisicao = partes[1];
                    int status = Integer.parseInt(partes[2].trim().split(" ")[0]);
                    int tamanho = Integer.parseInt(partes[2].trim().split(" ")[1]);
                    String agente = partes.length >= 6 ? partes[5] : "Desconhecido";

                    LogEntry log = new LogEntry(ip, dataHora, requisicao, status, tamanho, agente);
                    logs.add(log);
                }
            }


            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return logs;
    }
}
