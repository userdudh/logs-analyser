package br.upe.logsanalyser;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcaoEscolhida;

        List<LogEntry> logs = LeitorDeLog.lerLogs("access.log");

        do {
            System.out.println("\n-------------------------------");
            System.out.println("     ANALISADOR DE LOGS");
            System.out.println("-------------------------------");
            System.out.println("1 - Recursos grandes respondidos");
            System.out.println("2 - Requisições não respondidas");
            System.out.println("3 - Porcentagem por sistema operacional");
            System.out.println("4 - Média de requisições POST");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcaoEscolhida = teclado.nextInt();

            switch (opcaoEscolhida) {
                case 1:
                    System.out.println("=== Recursos grandes respondidos (acima de 10000 bytes) ===");
                    for (LogEntry log : logs) {
                        if (log.getTamanho() > 10000) {
                            System.out.println(log);
                        }
                    }
                    break;

                case 2:
                    System.out.println("=== Requisições não respondidas (status 4xx ou 5xx) ===");
                    for (LogEntry log : logs) {
                        int status = log.getStatus();
                        if (status >= 400 && status < 600) {
                            System.out.println(log);
                        }
                    }
                    break;

                case 3:
                    System.out.println("=== Porcentagem de requisições por sistema operacional ===");

                    int total = logs.size();
                    int windows = 0;
                    int linux = 0;
                    int mac = 0;
                    int outros = 0;

                    for (LogEntry log : logs) {
                        String agente = log.getAgenteUsuario().toLowerCase();

                        if (agente.contains("windows")) {
                            windows++;
                        } else if (agente.contains("linux")) {
                            linux++;
                        } else if (agente.contains("mac")) {
                            mac++;
                        } else {
                            outros++;
                        }
                    }

                    System.out.println("Windows: " + (windows * 100.0 / total) + "%");
                    System.out.println("Linux: " + (linux * 100.0 / total) + "%");
                    System.out.println("Mac: " + (mac * 100.0 / total) + "%");
                    System.out.println("Outros: " + (outros * 100.0 / total) + "%");
                    break;

                case 4:
                    System.out.println("=== Média de requisições POST ===");

                    int totalPOST = 0;

                    for (LogEntry log : logs) {
                        if (log.getRequisicao().startsWith("POST")) {
                            totalPOST++;
                        }
                    }

                    System.out.println("Quantidade de requisições POST: " + totalPOST);
                    System.out.println("Média: " + (double) totalPOST / logs.size());
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcaoEscolhida != 0);

        teclado.close();
    }
}
