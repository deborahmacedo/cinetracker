package model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeFilmes {
    private List<Filme> filmes;
    // construtor
    public GerenciadorDeFilmes() {
        this.filmes = new ArrayList<>();
    }

    // listar todos os filmes
    public List<Filme> listarFilmes() {
        return new ArrayList<>(filmes); // retorna a cópia da lista original de filmes
    }
    // dividir as listas de filme de acordo com seu status
    public List<Filme> listarPorStatus(StatusFilme statusDesejado) {
        List<Filme> resultado = new ArrayList<>();
        for (Filme f : filmes) {
            if (f.getStatus() == statusDesejado){ // comparar
                resultado.add(f); // lista resultado com filtro
            }
        }
        return resultado;
    }
    public void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }
    public void removerFilme(Filme filme) {
        filmes.remove(filme);
    }

    public boolean editarFilme(String id, String novoTitulo, double novaNota, String novaReview, StatusFilme novoStatus) {
        for (Filme f : filmes) {
            if (f.getId().equals(id)) {
                if (novoTitulo != null) f.setTitulo(novoTitulo);
                double valor = 0.00; // consertar depois
                if (novaNota == 0.00) f.setNota(novaNota);
                if (novaReview != null) f.setReview(novaReview);
                if (novoStatus != null) f.setStatus(novoStatus);

                return true;
            }
        }
        return false;
    }
    public boolean marcarComoAssistido(String id, boolean assistido) {
        for (Filme f : filmes) { // percorre os filmes
            if (f.getId().equals(id)) { // compara o id do filme
                if (assistido) {
                    f.setStatus(StatusFilme.ASSISTIDO); // muda o status para 'assistido'
                } else {
                    f.setStatus(StatusFilme.PARA_ASSISTIR); // muda o status para 'para_assistir'
                }
                return true;
            }
        }
        return false;
    }
}
