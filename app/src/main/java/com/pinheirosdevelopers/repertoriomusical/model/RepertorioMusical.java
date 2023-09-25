package com.pinheirosdevelopers.repertoriomusical.model;

import android.content.Intent;

import java.io.Serializable;

public class RepertorioMusical implements Serializable {
    private Integer id;
    private String NomeDaMusica;
    private String NomeDoArtistaBanda;
    private String TomMusical;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDaMusica() {
        return NomeDaMusica;
    }

    public void setNomeDaMusica(String nomeDaMusica) {
        NomeDaMusica = nomeDaMusica;
    }

    public String getNomeDoArtistaBanda() {
        return NomeDoArtistaBanda;
    }

    public void setNomeDoArtistaBanda(String nomeDoArtistaBanda) {
        NomeDoArtistaBanda = nomeDoArtistaBanda;
    }

    public String getTomMusical() {
        return TomMusical;
    }

    public void setTomMusical(String tomMusical) {
        TomMusical = tomMusical;
    }
}
