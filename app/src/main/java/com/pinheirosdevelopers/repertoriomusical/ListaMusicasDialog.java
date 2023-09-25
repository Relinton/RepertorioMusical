package com.pinheirosdevelopers.repertoriomusical;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.pinheirosdevelopers.repertoriomusical.adapter.AgendaDeEventoAdapter;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

import java.util.List;

public class ListaMusicasDialog extends Dialog {
    private List<AgendaDeEvento> listaDeMusicas;

    public ListaMusicasDialog(@NonNull Context context, List<AgendaDeEvento> listaDeMusicas) {
        super(context);
        this.listaDeMusicas = listaDeMusicas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_musicas);

        ListView listViewMusicas = findViewById(R.id.listViewMusicas);

        // Configure um adaptador para exibir as músicas no ListView
        AgendaDeEventoAdapter adapter = new AgendaDeEventoAdapter(getContext(), listaDeMusicas);
        listViewMusicas.setAdapter(adapter);
    }
}
