package br.upe.logsanalyser.app;

import br.upe.logsanalyser.model.LogEntry;
import br.upe.logsanalyser.service.LogReader;
import br.upe.logsanalyser.service.LogAnalyser;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        LogReader leitor = new LogReader();

        
        List<LogEntry> logs = leitor.lerLogs("C:\\Users\\Duda\\Documents\\logs-analyser\\logs-analyser\\src\\main\\java\\br\\upe\\logsanalyser\\resources\\access.log");

        LogAnalyser analyser = new LogAnalyser(logs);

        int opcaoEscolhida;

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
                    analyser.mostrarMaioresRespostas();
                    break;

                case 2:
                    analyser.mostrarNaoRespondidas();
                    break;

                case 3:
                    analyser.mostrarSistemasOperacionais2021();
                    break;

                case 4:
                    analyser.mostrarMediaPOST2021();
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
