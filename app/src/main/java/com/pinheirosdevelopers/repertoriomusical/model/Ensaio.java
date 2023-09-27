package com.pinheirosdevelopers.repertoriomusical.model;

import java.io.Serializable;

public class Ensaio implements Serializable {

    private Integer Id;
    private String DescricaoDoEnsaio;
    private String EnderecoDoEnsaio;
    private String DataDoEnsaio;
    private String HoraDoEnsaio;
    private String MusicasASeremTocadas;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescricaoDoEnsaio() {
        return DescricaoDoEnsaio;
    }

    public void setDescricaoDoEnsaio(String descricaoDoEnsaio) {
        DescricaoDoEnsaio = descricaoDoEnsaio;
    }

    public String getEnderecoDoEnsaio() {
        return EnderecoDoEnsaio;
    }

    public void setEnderecoDoEnsaio(String enderecoDoEnsaio) {
        EnderecoDoEnsaio = enderecoDoEnsaio;
    }

    public String getDataDoEnsaio() {
        return DataDoEnsaio;
    }

    public void setDataDoEnsaio(String dataDoEnsaio) {
        DataDoEnsaio = dataDoEnsaio;
    }

    public String getHoraDoEnsaio() {
        return HoraDoEnsaio;
    }

    public void setHoraDoEnsaio(String horaDoEnsaio) {
        HoraDoEnsaio = horaDoEnsaio;
    }

    public String getMusicasASeremTocadas() {
        return MusicasASeremTocadas;
    }

    public void setMusicasASeremTocadas(String musicasASeremTocadas) {
        MusicasASeremTocadas = musicasASeremTocadas;
    }
}
