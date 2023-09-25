package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;
import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;

import java.util.ArrayList;
import java.util.List;

public class AgendaDeEventoDAO {

    private AgendaDeEventoConexao conexao;
    private SQLiteDatabase banco;

    public AgendaDeEventoDAO(Context context)
    {
        conexao = new AgendaDeEventoConexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(AgendaDeEvento agendaDeEvento)
    {
        ContentValues values = new ContentValues();
        values.put("descricaoDoEvento", agendaDeEvento.getDescricaoDoEvento());
        values.put("enderecoDoEvento", agendaDeEvento.getEnderecoDoEvento());
        values.put("dataDoEvento", agendaDeEvento.getDataDoEvento());
        values.put("horaDoEvento", agendaDeEvento.getHoraDoEvento());
        values.put("musicasASeremTocadas", agendaDeEvento.getMusicasASeremTocadas());

        return banco.insert("agendaDeEvento", null, values);
    }

    public List<AgendaDeEvento> obterTodos()
    {
        List<AgendaDeEvento> agendaDeEventos = new ArrayList<>();
        Cursor cursor = banco.query("agendaDeEvento", new String[]{"id", "descricaoDoEvento", "enderecoDoEvento", "dataDoEvento", "horaDoEvento", "musicasASeremTocadas"},
                null, null, null, null, null);
        while (cursor.moveToNext())
        {
            AgendaDeEvento agendaDeEvento = new AgendaDeEvento();
            agendaDeEvento.setId(cursor.getInt(0));
            agendaDeEvento.setDescricaoDoEvento(cursor.getString(1));
            agendaDeEvento.setEnderecoDoEvento(cursor.getString(2));
            agendaDeEvento.setDataDoEvento(cursor.getString(3));
            agendaDeEvento.setHoraDoEvento(cursor.getString(4));
            agendaDeEvento.setMusicasASeremTocadas(cursor.getString(5));
            agendaDeEventos.add(agendaDeEvento);
        }
        return agendaDeEventos;
    }

    public void excluir(AgendaDeEvento agendaDeEvento)
    {
        banco.delete("agendaDeEvento", "id = ?", new String[]{agendaDeEvento.getId().toString()});
    }

    public void atualizar(AgendaDeEvento agendaDeEvento)
    {
        ContentValues values = new ContentValues();
        values.put("descricaoDoEvento", agendaDeEvento.getDescricaoDoEvento());
        values.put("enderecoDoEvento", agendaDeEvento.getEnderecoDoEvento());
        values.put("dataDoEvento", agendaDeEvento.getDataDoEvento());
        values.put("horaDoEvento", agendaDeEvento.getHoraDoEvento());
        values.put("musicasASeremTocadas", agendaDeEvento.getMusicasASeremTocadas());
        banco.update("agendaDeEvento", values, "id = ?", new String[]{agendaDeEvento.getId().toString()});
    }
}
