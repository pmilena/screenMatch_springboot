package com.milena.screenmatch.model;

public class Episodio {

    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private Integer temporada;

    public Episodio(Integer temporada, DadosEpisodio dadosEpisodio) {

    this.temporada=temporada;
    this.titulo=dadosEpisodio.titulo();
    this.numeroEpisodio=dadosEpisodio.numero();

    try {
        this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
    } catch (NumberFormatException e) {
        this.avaliacao = 0.0;;
    }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", temporada=" + temporada;
    }
}
