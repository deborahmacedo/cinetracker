package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeFilmes {

    // Lista onde todos os filmes ficam armazenados em memória enquanto o programa está rodando
    private List<Filme> filmes;

    // Nome do arquivo onde os dados serão salvos em JSON
    private static final String ARQUIVO_JSON = "meus_filmes.json";

    // Objeto responsável por converter objetos Java ↔ JSON
    private final Gson gson;

  
    public GerenciadorDeFilmes() {

        // Cria um Gson formatado (JSON bonito)
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        // Ao iniciar o sistema, tenta carregar os filmes gravados no JSON
        this.filmes = carregarDados();
    }

 
    private List<Filme> carregarDados() {

        File arquivo = new File(ARQUIVO_JSON);

        // Se o arquivo não existir, significa primeira execução → lista vazia
        if (!arquivo.exists()) return new ArrayList<>();

        try (Reader reader = new FileReader(ARQUIVO_JSON)) {

            // Informamos ao Gson que queremos converter para "ArrayList<Filme>"
            Type listType = new TypeToken<ArrayList<Filme>>() {}.getType();

            // Converte o JSON para uma lista de filmes
            List<Filme> dados = gson.fromJson(reader, listType);

            // Se o JSON estava vazio, retorna lista nova
            return (dados != null) ? dados : new ArrayList<>();

        } catch (IOException e) {
            // Se der erro ao tentar ler o arquivo, imprime o erro e retorna lista nova
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    
    private void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVO_JSON)) {

            // Converte a lista de filmes para JSON e escreve no arquivo
            gson.toJson(filmes, writer);

        } catch (IOException e) {
            // Se ocorrer erro ao salvar o arquivo, exibe no console
            e.printStackTrace();
        }
    }

    public List<Filme> listarFilmes() {
        return new ArrayList<>(filmes);
    }

    

    public List<Filme> listarPorStatus(StatusFilme statusDesejado) {

        List<Filme> resultado = new ArrayList<>();

        // Varre toda a lista e seleciona filmes que possuem o status solicitado
        for (Filme f : filmes) {
            if (f.getStatus() == statusDesejado) {
                resultado.add(f);
            }
        }
        return resultado;
    }

   
    public void adicionarFilme(Filme filme) {
        filmes.add(filme);    // adiciona na lista
        salvarDados();        // salva no JSON
    }

    
    public void removerFilme(Filme filme) {
        filmes.remove(filme);
        salvarDados();        // persiste mudança
    }

    public boolean editarFilme(String id, String novoTitulo, double novaNota, String novaReview, StatusFilme novoStatus) {

        for (Filme f : filmes) {

            // Primeiro encontra o filme pelo ID
            if (f.getId().equals(id)) {

                // Atualiza apenas os campos enviados pelo usuário
                if (novoTitulo != null && !novoTitulo.isEmpty()) f.setTitulo(novoTitulo);

                if (novaNota >= 0) f.setNota(novaNota);

                if (novaReview != null) f.setReview(novaReview);

                if (novoStatus != null) f.setStatus(novoStatus);

                salvarDados();
                return true; // edição concluída
            }
        }
        return false; // filme não encontrado
    }


    public boolean marcarComoAssistido(String id, boolean assistido) {

        for (Filme f : filmes) {

            // procura o filme pelo id
            if (f.getId().equals(id)) {

                // Define o novo status
                if (assistido) {
                    f.setStatus(StatusFilme.ASSISTIDO);
                } else {
                    f.setStatus(StatusFilme.PARA_ASSISTIR);
                }

                salvarDados();
                return true;
            }
        }
        return false; // filme não encontrado
    }
}

