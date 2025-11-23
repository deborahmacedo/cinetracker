 package model;

public class Filme {

    private String titulo;

    private double nota;

    private String id;

    private StatusFilme status;

    public String review;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public StatusFilme getStatus() {
        return status;
    }

    public void setStatus(StatusFilme status) {
        this.status = status;
    }

    //construtor
    public Filme(String titulo, String id, double nota, StatusFilme status) {
        this.titulo = titulo;
        this.id = id;
        this.nota = nota;
        this.status = status;
    }
    // cons vazio para o json
    public Filme() {}

}
