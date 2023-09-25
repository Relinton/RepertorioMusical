package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AgendaDeEventoConexao extends SQLiteOpenHelper {

    private static final String name = "agendadeevento.db";
    private static final int version = 1;

    public AgendaDeEventoConexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table agendaDeEvento(id integer primary key autoincrement, " +
                "descricaoDoEvento varchar(250), enderecoDoEvento varchar(250), dataDoEvento varchar(250), horaDoEvento varchar(250), musicasASeremTocadas varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
