public class Filme {

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    String titulo;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    double nota;

    public Enum status {
        ASSISTIDO,
        PARA_ASSISTIR;
    }



}