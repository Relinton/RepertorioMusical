package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.adapter.AgendaDeEventoAdapter;
import com.pinheirosdevelopers.repertoriomusical.conexao.AgendaDeEventoDAO;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

import java.util.ArrayList;
import java.util.List;

public class ListaNotificacaoActivity extends AppCompatActivity {

    private ListView listView;
    private AgendaDeEventoDAO dao;
    private List<AgendaDeEvento> agendasDeEventos;
    private List<AgendaDeEvento> agendaDeEventosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notificacao);

        try {
            this.getSupportActionBar().show();
        }
        catch (NullPointerException e) {
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializador();
        dao = new AgendaDeEventoDAO(this);
        agendasDeEventos = dao.obterTodos();
        agendaDeEventosFiltrados.addAll(agendasDeEventos);
        TextView txtVazia = findViewById(R.id.txt_lista_vazia_notificacao);

        AgendaDeEventoAdapter adaptador = new AgendaDeEventoAdapter(this, agendaDeEventosFiltrados);
        listView.setAdapter(adaptador);

        ListView listaNotificacao = findViewById(R.id.lista_notificacao);
        if (listaNotificacao.getAdapter() == null || listaNotificacao.getAdapter().getCount() == 0) {
            txtVazia.setVisibility(View.VISIBLE);
            listaNotificacao.setVisibility(View.GONE);
        } else {
            txtVazia.setVisibility(View.GONE);
            listaNotificacao.setVisibility(View.VISIBLE);
        }

        if(adaptador.getCount() != 0)
        {
            registerForContextMenu(listView);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaNotificacaoActivity.this);
            builder.setTitle("Mensagem");
            builder.setMessage("Lista de notificações vazia. Não se preocupe, quando você adicionar um Evento, a lista de notificações será atualizada.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_de_busca, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarPorDescricaoDoEventoSearch(s);
                return false;
            }
        });

        return true;
    }

    public void procurarPorDescricaoDoEventoSearch(String descricaoDoEvento)
    {
        agendaDeEventosFiltrados.clear();
        for (AgendaDeEvento e : agendasDeEventos)
        {
            if (e.getDescricaoDoEvento().toLowerCase().contains(descricaoDoEvento.toLowerCase()))
            {
                agendaDeEventosFiltrados.add(e);
            }
        }
        listView.invalidateViews();
    }

    private void inicializador() {
        listView = findViewById(R.id.lista_notificacao);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        agendasDeEventos = dao.obterTodos();
        agendaDeEventosFiltrados.clear();
        agendaDeEventosFiltrados.addAll(agendasDeEventos);
        listView.invalidateViews();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListaNotificacaoActivity.this, MenuInterativoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}