package br.upe.logsanalyser.model;


public class LogEntry {
    private String ip;
    private String dataHora;
    private String requisicao;
    private int status;
    private int tamanhoResposta;
    private String navegadorCliente;


    public LogEntry(String ip, String dataHora, String requisicao, int status, int tamanhoResposta, String navegadorCliente) {
        this.ip = ip;
        this.dataHora = dataHora;
        this.requisicao = requisicao;
        this.status = status;
        this.tamanhoResposta = tamanhoResposta;
        this.navegadorCliente = navegadorCliente;
    }

  
    public String getIp() {
        return ip;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getRequisicao() {
        return requisicao;
    }

    public int getStatus() {
        return status;
    }

    public int getTamanhoResposta() {
        return tamanhoResposta;
    }

    public String getNavegadorCliente() {
        return navegadorCliente;
    }

 
    public boolean foiRespondidaComSucesso() {
        return status >= 200 && status < 300;
    }

    public boolean isPost() {
        return requisicao != null && requisicao.toUpperCase().startsWith("POST");
    }


    public String detectarSistemaOperacional() {
        if (navegadorCliente == null) {
            return "Desconhecido";
        }

        String agente = navegadorCliente.toLowerCase();

        if (agente.contains("windows")) {
            return "Windows";
        } else if (agente.contains("linux")) {
            return "Linux";
        } else if (agente.contains("mac")) {
            return "MacOS";
        } else if (agente.contains("android")) {
            return "Android";
        } else if (agente.contains("iphone") || agente.contains("ios")) {
            return "iOS";
        } else {
            return "Outro";
        }
    }


    @Override
    public String toString() {
        return String.format("%s - [%s] \"%s\" %d %d \"%s\"",
                ip, dataHora, requisicao, status, tamanhoResposta, navegadorCliente);
    }
}
