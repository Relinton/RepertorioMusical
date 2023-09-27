package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pinheirosdevelopers.repertoriomusical.model.Ensaio;

import java.util.ArrayList;
import java.util.List;

public class EnsaioDAO {

    private EnsaioConexao conexao;
    private SQLiteDatabase banco;

    public EnsaioDAO(Context context)
    {
        conexao = new EnsaioConexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Ensaio ensaio)
    {
        ContentValues values = new ContentValues();
        values.put("descricaoDoEnsaio", ensaio.getDescricaoDoEnsaio());
        values.put("enderecoDoEnsaio", ensaio.getEnderecoDoEnsaio());
        values.put("dataDoEnsaio", ensaio.getDataDoEnsaio());
        values.put("horaDoEnsaio", ensaio.getHoraDoEnsaio());
        values.put("musicasASeremTocadas", ensaio.getMusicasASeremTocadas());

        return banco.insert("ensaio", null, values);
    }

    public List<Ensaio> obterTodos()
    {
        List<Ensaio> ensaios = new ArrayList<>();
        Cursor cursor = banco.query("ensaio", new String[]{"id", "descricaoDoEnsaio", "enderecoDoEnsaio", "dataDoEnsaio", "horaDoEnsaio", "musicasASeremTocadas"},
                null, null, null, null, null);
        while (cursor.moveToNext())
        {
            Ensaio ensaio = new Ensaio();
            ensaio.setId(cursor.getInt(0));
            ensaio.setDescricaoDoEnsaio(cursor.getString(1));
            ensaio.setEnderecoDoEnsaio(cursor.getString(2));
            ensaio.setDataDoEnsaio(cursor.getString(3));
            ensaio.setHoraDoEnsaio(cursor.getString(4));
            ensaio.setMusicasASeremTocadas(cursor.getString(5));
            ensaios.add(ensaio);
        }
        return ensaios;
    }

    public void excluir(Ensaio ensaio)
    {
        banco.delete("ensaio", "id = ?", new String[]{ensaio.getId().toString()});
    }

    public void atualizar(Ensaio ensaio)
    {
        ContentValues values = new ContentValues();
        values.put("descricaoDoEnsaio", ensaio.getDescricaoDoEnsaio());
        values.put("enderecoDoEnsaio", ensaio.getEnderecoDoEnsaio());
        values.put("dataDoEnsaio", ensaio.getDataDoEnsaio());
        values.put("horaDoEnsaio", ensaio.getHoraDoEnsaio());
        values.put("musicasASeremTocadas", ensaio.getMusicasASeremTocadas());
        banco.update("ensaio", values, "id = ?", new String[]{ensaio.getId().toString()});
    }
}
