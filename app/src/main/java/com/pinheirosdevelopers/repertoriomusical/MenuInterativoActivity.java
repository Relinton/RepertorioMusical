package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuInterativoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_interativo);
    }

    public void RepertorioMusical(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaRepertorioMusicalActivity.class));
    }

    public void AgendaDeEventos(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaAgendaDeEventoActivity.class));
    }

    public void Ensaios(View view) {
        //startActivity(new Intent(MenuInterativoActivity.this, MenuInterativoActivity.class));
    }

    public void Notificacoes(View view) {
        //startActivity(new Intent(MenuInterativoActivity.this, MenuInterativoActivity.class));
    }
}