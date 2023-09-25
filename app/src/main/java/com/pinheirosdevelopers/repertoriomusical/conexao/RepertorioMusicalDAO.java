package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;

import java.util.ArrayList;
import java.util.List;

public class RepertorioMusicalDAO {

    private RepertorioMusicalConexao conexao;
    private SQLiteDatabase banco;

    public RepertorioMusicalDAO(Context context)
    {
        conexao = new RepertorioMusicalConexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(RepertorioMusical repertorioMusical)
    {
        ContentValues values = new ContentValues();
        values.put("nomeDaMusica", repertorioMusical.getNomeDaMusica());
        values.put("nomeDoArtistaBanda", repertorioMusical.getNomeDoArtistaBanda());
        values.put("tomMusical", repertorioMusical.getTomMusical());

        return banco.insert("repertorioMusical", null, values);
    }

    public List<RepertorioMusical> obterTodos()
    {
        List<RepertorioMusical> repertorioMusicais = new ArrayList<>();
        Cursor cursor = banco.query("repertorioMusical", new String[]{"id", "nomeDaMusica", "nomeDoArtistaBanda", "tomMusical"},
                null, null, null, null, null);
        while (cursor.moveToNext())
        {
            RepertorioMusical repertorioMusical = new RepertorioMusical();
            repertorioMusical.setId(cursor.getInt(0));
            repertorioMusical.setNomeDaMusica(cursor.getString(1));
            repertorioMusical.setNomeDoArtistaBanda(cursor.getString(2));
            repertorioMusical.setTomMusical(cursor.getString(3));
            repertorioMusicais.add(repertorioMusical);
        }
        return repertorioMusicais;
    }

    public void excluir(RepertorioMusical repertorioMusical)
    {
        banco.delete("repertorioMusical", "id = ?", new String[]{repertorioMusical.getId().toString()});
    }

    public void atualizar(RepertorioMusical repertorioMusical)
    {
        ContentValues values = new ContentValues();
        values.put("nomeDaMusica", repertorioMusical.getNomeDaMusica());
        values.put("nomeDoArtistaBanda", repertorioMusical.getNomeDoArtistaBanda());
        values.put("tomMusical", repertorioMusical.getTomMusical());
        banco.update("repertorioMusical", values, "id = ?", new String[]{repertorioMusical.getId().toString()});
    }
}
