package br.upe.logsanalyser;

public class LogEntry {
    private String ip;
    private String dataHora;
    private String requisicao;
    private int status;
    private int tamanho;
    private String agenteUsuario;

    public LogEntry(String ip, String dataHora, String requisicao, int status, int tamanho, String agenteUsuario) {
        this.ip = ip;
        this.dataHora = dataHora;
        this.requisicao = requisicao;
        this.status = status;
        this.tamanho = tamanho;
        this.agenteUsuario = agenteUsuario;
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

    public int getTamanho() {
        return tamanho;
    }

    public String getAgenteUsuario() {
        return agenteUsuario;
    }

    @Override
    public String toString() {
        return String.format("%s - [%s] \"%s\" %d %d \"%s\"",
                ip, dataHora, requisicao, status, tamanho, agenteUsuario);
    }
}
