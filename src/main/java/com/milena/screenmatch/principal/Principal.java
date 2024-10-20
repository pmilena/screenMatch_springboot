package com.milena.screenmatch.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.milena.screenmatch.model.DadosEpisodio;
import com.milena.screenmatch.model.DadosSerie;
import com.milena.screenmatch.model.DadosTemporada;
import com.milena.screenmatch.model.Episodio;
import com.milena.screenmatch.service.ConsumoApi;
import com.milena.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "https://omdbapi.com/?t=";
    private final String API_KEY = "&apikey=8ffb465d";
    private ConverteDados conversor = new ConverteDados();
    private DadosSerie dadosSerie;
    private List<DadosTemporada> addTemporadas = new ArrayList<>();

    //
    public void ExibeMenu() throws JsonProcessingException {

        System.out.println("Qual série que deseja pesquisar:");
        String serieEscolhida = leitor.nextLine();
        var json = consumo.obterDados(ENDERECO + serieEscolhida.replace(" ", "+") + API_KEY);
        dadosSerie = conversor.obterDados(json, DadosSerie.class);

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + serieEscolhida.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            addTemporadas.add(dadosTemporada);
        }
        addTemporadas.forEach(System.out::println);

        /*for(int i = 0; i< dadosSerie.totalTemporadas();i++){
            List<DadosEpisodio> dadosEpisodios = addTemporadas.get(i).listaEpisodios();
            for (int j=0;j<dadosEpisodios.size();j++) {
                System.out.println(dadosEpisodios.get(j).titulo());
            }
        }*/

        addTemporadas.forEach(t -> t.listaEpisodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> avaliacaoEpisodios = addTemporadas.stream()
                .flatMap(t -> t.listaEpisodios()
                        .stream())
                .collect(Collectors.toList());


        List<DadosEpisodio> avaliacoes = avaliacaoEpisodios.stream().filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .collect(Collectors.toList());


        System.out.println("TOP 5");
        avaliacoes.forEach(System.out::println);

List<Episodio> episodios = addTemporadas.stream()
        .flatMap(t -> t.listaEpisodios()
                .stream().map(e->new Episodio(t.numero(),e))).collect(Collectors.toList());

        episodios.forEach(System.out::println);

    }
}


