package model;

public class Filme {

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String titulo;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    private nota;

    private StatusFilme status;

    public StatusFilme getStatus() {
        return status;
    }

    public void setStatus(StatusFilme status) {
        this.status = status;
    }

    //construtor
    public Filme(String titulo, int nota, StatusFilme status) {
        this.titulo = titulo;
        this.nota = nota;
        this.status = status;
    }
}