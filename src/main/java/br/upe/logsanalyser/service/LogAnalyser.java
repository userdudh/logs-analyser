package br.upe.logsanalyser.service;

import br.upe.logsanalyser.model.LogEntry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class LogAnalyser {

    private List<LogEntry> entradas;

    public LogAnalyser(List<LogEntry> entradas) {
        this.entradas = entradas;
    }


    public void mostrarMaioresRespostas() {
        System.out.println("\nRequisições com resposta bem-sucedida e objetos > 2000 bytes");

        List<String> linhas = new ArrayList<>();

        for (LogEntry entrada : entradas) {
            int codigo = entrada.getStatus();
            int tamanho = entrada.getTamanhoResposta();

            if (codigo >= 200 && codigo < 300 && tamanho > 2000) {
                String linha = String.format("%d %d %s", codigo, tamanho, entrada.getIp());
                //System.out.println(linha);
                linhas.add(linha);
            }
        }

        salvarEmArquivo("recursosGrandes.txt", linhas);
    }

    public void mostrarNaoRespondidas() {
        System.out.println("\nRequisições não respondidas em Nov/2021");

        List<String> linhas = new ArrayList<>();

        for (LogEntry entrada : entradas) {
            int status = entrada.getStatus();
            String data = entrada.getDataHora();
            String recurso = entrada.getRequisicao();

            String[] partesData = data.split("/");
            if (partesData.length >= 3) {
                String mes = partesData[1];
                String ano = partesData[2].split(":")[0];

                if (status >= 400 && status < 500 &&
                        mes.equalsIgnoreCase("Apr") && ano.equals("2021")) {


                    String url = extrairUrl(recurso);
                    String linha = String.format("%d \"%s\" Nov/2021", status, url);
                    linhas.add(linha);
                }
            }
        }

        salvarEmArquivo("naoRespondidos.txt", linhas);
        System.out.println("Total salvo: " + linhas.size());
    }

    private String extrairUrl(String requisicao) {
        String[] partes = requisicao.split(" ");
        if (partes.length >= 2) {
            return partes[1];
        }
        return "URL inválida";
    }


    public void mostrarSistemasOperacionais2021() {
        System.out.println("\nArquivo de percentuais por sistema operacional (2021)");

        Map<String, Integer> contagemSO = new HashMap<>();
        int total = 0;

        for (LogEntry entrada : entradas) {
            if (!entrada.getDataHora().contains("2021")) continue;

            String agente = entrada.getNavegadorCliente().toLowerCase();
            String so;

            if (agente.contains("android") || agente.contains("mobile")) {
                so = "Mobile";
            } else if (agente.contains("windows")) {
                so = "Windows";
            } else if (agente.contains("macintosh") || agente.contains("mac os")) {
                so = "Macintosh";
            } else if (agente.contains("ubuntu")) {
                so = "Ubuntu";
            } else if (agente.contains("fedora")) {
                so = "Fedora";
            } else if (agente.contains("x11")) {
                so = "Linux, outros";
            } else {
                continue;
            }

            contagemSO.put(so, contagemSO.getOrDefault(so, 0) + 1);
            total++;
        }

        List<String> linhas = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : contagemSO.entrySet()) {
            double porcentagem = entry.getValue() * 100.0 / total;
            String linha = String.format("%s %.4f", entry.getKey(), porcentagem);
            linhas.add(linha);
        }

        salvarEmArquivo("sistemasOperacionais.txt", linhas);
    }


    private void salvarEmArquivo(String nomeArquivo, List<String> linhas) {
        try {
            File pasta = new File("Análise");
            if (!pasta.exists()) {
                pasta.mkdirs(); // cria a pasta se não existir
            }

            File arquivo = new File(pasta, nomeArquivo);
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));

            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }

            writer.close();
            System.out.println("Arquivo salvo em: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void mostrarMediaPOST2021() {
        System.out.println("\nMédia de tamanho das respostas do tipo POST (sucesso, 2021):");

        int total = 0;
        int somaTamanhos = 0;

        for (LogEntry entrada : entradas) {
            if (entrada.isPost() &&
                    entrada.foiRespondidaComSucesso() &&
                    entrada.getDataHora().contains("2021")) {

                somaTamanhos += entrada.getTamanhoResposta();
                total++;
            }
        }

        if (total == 0) {
            System.out.println("Nenhuma requisição POST com sucesso encontrada em 2021.");
        } else {
            double media = (double) somaTamanhos / total;
            System.out.printf("Média: %.2f bytes\n", media);
        }
    }

}
