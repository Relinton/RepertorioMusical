package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EnsaioConexao extends SQLiteOpenHelper {

    private static final String name = "ensaio.db";
    private static final int version = 1;

    public EnsaioConexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ensaio(id integer primary key autoincrement, " +
                "descricaoDoEnsaio varchar(250), enderecoDoEnsaio varchar(250), dataDoEnsaio varchar(250), horaDoEnsaio varchar(250), musicasASeremTocadas varchar(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

