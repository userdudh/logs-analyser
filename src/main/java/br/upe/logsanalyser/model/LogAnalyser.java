package br.upe.logsanalyser;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Esta classe realiza a análise dos logs.
 * Ela utiliza uma lista de LogEntry para calcular os dados necessários.
 */
public class LogAnalyser {

    private List<LogEntry> entradas;

    /**
     * Construtor que recebe a lista de entradas de log.
     */
    public LogAnalyser(List<LogEntry> entradas) {
        this.entradas = entradas;
    }

    /**
     * Mostra as requisições com maior tamanho de resposta.
     * Considera as maiores 5 por padrão.
     */
    public void mostrarMaioresRespostas() {
        System.out.println("\nAs 5 maiores respostas em bytes:");
        entradas.stream()
                .sorted((a, b) -> Integer.compare(b.getTamanhoResposta(), a.getTamanhoResposta()))
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * Mostra as requisições que não foram respondidas com sucesso (status diferente de 2xx).
     */
    public void mostrarNaoRespondidas() {
        System.out.println("\nRequisições não respondidas com sucesso:");
        for (LogEntry entrada : entradas) {
            if (!entrada.foiRespondidaComSucesso()) {
                System.out.println(entrada);
            }
        }
    }

    /**
     * Calcula e exibe a porcentagem de requisições por sistema operacional.
     */
    public void mostrarPorcentagemPorSistemaOperacional() {
        System.out.println("\nPorcentagem de requisições por Sistema Operacional:");

        Map<String, Integer> contagemSO = new HashMap<>();
        int total = 0;

        for (LogEntry entrada : entradas) {
            String so = entrada.detectarSistemaOperacional();
            contagemSO.put(so, contagemSO.getOrDefault(so, 0) + 1);
            total++;
        }

        for (Map.Entry<String, Integer> par : contagemSO.entrySet()) {
            String so = par.getKey();
            int quantidade = par.getValue();
            double porcentagem = (quantidade * 100.0) / total;
            System.out.printf("%s: %.2f%%\n", so, porcentagem);
        }
    }

    /**
     * Calcula a média de tamanho das respostas do tipo POST.
     */
    public void mostrarMediaRequisicoesPOST() {
        System.out.println("\nMédia de tamanho das respostas do tipo POST:");

        int totalPost = 0;
        int somaTamanhos = 0;

        for (LogEntry entrada : entradas) {
            if (entrada.isPost()) {
                somaTamanhos += entrada.getTamanhoResposta();
                totalPost++;
            }
        }

        if (totalPost == 0) {
            System.out.println("Nenhuma requisição POST encontrada.");
        } else {
            double media = (double) somaTamanhos / totalPost;
            System.out.printf("Média: %.2f bytes\n", media);
        }
    }
}
