package com.pinheirosdevelopers.repertoriomusical.model;

import java.io.Serializable;

public class AgendaDeEvento implements Serializable {
    private Integer Id;
    private String DescricaoDoEvento;
    private String EnderecoDoEvento;
    private String DataDoEvento;
    private String HoraDoEvento;
    private String MusicasASeremTocadas;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescricaoDoEvento() {
        return DescricaoDoEvento;
    }

    public void setDescricaoDoEvento(String descricaoDoEvento) {
        DescricaoDoEvento = descricaoDoEvento;
    }

    public String getEnderecoDoEvento() {
        return EnderecoDoEvento;
    }

    public void setEnderecoDoEvento(String enderecoDoEvento) {
        EnderecoDoEvento = enderecoDoEvento;
    }

    public String getDataDoEvento() {
        return DataDoEvento;
    }

    public void setDataDoEvento(String dataDoEvento) {
        DataDoEvento = dataDoEvento;
    }

    public String getHoraDoEvento() {
        return HoraDoEvento;
    }

    public void setHoraDoEvento(String horaDoEvento) {
        HoraDoEvento = horaDoEvento;
    }

    public String getMusicasASeremTocadas() {
        return MusicasASeremTocadas;
    }

    public void setMusicasASeremTocadas(String musicasASeremTocadas) {
        MusicasASeremTocadas = musicasASeremTocadas;
    }
}
