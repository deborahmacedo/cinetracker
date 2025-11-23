package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeFilmes {
    private List<Filme> filmes;
    private static final String ARQUIVO_JSON = "meus_filmes.json";
    private final Gson gson; // converter JSON

    // construtor
    public GerenciadorDeFilmes() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.filmes = carregarDados(); // Carrega ao iniciar
    }
    private List<Filme> carregarDados() {
        File arquivo = new File(ARQUIVO_JSON);
        if (!arquivo.exists()) return new ArrayList<>(); // verifica se o arquivo existe

        try (Reader reader = new FileReader(ARQUIVO_JSON)) {
            Type listType = new TypeToken<ArrayList<Filme>>(){}.getType();
            List<Filme> dados = gson.fromJson(reader, listType);

            // Substituindo o operador ternário por if
            if (dados != null) {
                return dados;
            } else {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    // salva a lista filmes em um arquivo JSON
    private void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVO_JSON)) { // abre um FileWriter apontando para o arquivo em ARQUIVO_JSON
            gson.toJson(filmes, writer); // objeto gson serializa a lista filmes para JSON e escreve direto no arquivo
        } catch (IOException e) { // se algo falhar ao acessar o arquivo imprime o erro
            e.printStackTrace();
        }
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
        salvarDados();
    }
    public void removerFilme(Filme filme) {
        filmes.remove(filme);
        salvarDados();
    }

    public boolean editarFilme(String id, String novoTitulo, double novaNota, String novaReview, StatusFilme novoStatus) {
        for (Filme f : filmes) {
            if (novoTitulo != null && !novoTitulo.isEmpty()) f.setTitulo(novoTitulo);
            if (novaNota >= 0) f.setNota(novaNota);
            if (novaReview != null) f.setReview(novaReview);
            if (novoStatus != null) f.setStatus(novoStatus);
                salvarDados();
                return true;
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
                salvarDados();
                return true;
            }
        }
        return false;
    }
}
