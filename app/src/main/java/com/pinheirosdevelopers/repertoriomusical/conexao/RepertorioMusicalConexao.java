package com.pinheirosdevelopers.repertoriomusical.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RepertorioMusicalConexao extends SQLiteOpenHelper {

    private static final String name = "repertoriomusical.db";
    private static final int version = 1;

    public RepertorioMusicalConexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table repertorioMusical(id integer primary key autoincrement, " +
                "nomeDaMusica varchar(250), nomeDoArtistaBanda varchar(250), tomMusical varchar(250))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

