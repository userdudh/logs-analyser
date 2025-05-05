package br.upe.logsanalyser.model;

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

                    String requisicao = partes[1];
                    int status = Integer.parseInt(partes[2].trim().split(" ")[0]);
                    int tamanho = Integer.parseInt(partes[2].trim().split(" ")[1]);
                    String agente = partes.length >= 6 ? partes[5] : "Desconhecido";

                    LogEntry log = new LogEntry(ip, requisicao, status, tamanho, agente);
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
