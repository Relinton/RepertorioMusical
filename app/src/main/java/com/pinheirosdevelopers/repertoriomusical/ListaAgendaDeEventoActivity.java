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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinheirosdevelopers.repertoriomusical.adapter.AgendaDeEventoAdapter;
import com.pinheirosdevelopers.repertoriomusical.conexao.AgendaDeEventoDAO;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

import java.util.ArrayList;
import java.util.List;

public class ListaAgendaDeEventoActivity extends AppCompatActivity {

    private ListView listView;
    private AgendaDeEventoDAO dao;
    private List<AgendaDeEvento> agendasDeEventos;
    private List<AgendaDeEvento> agendaDeEventosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_agenda_de_evento);

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
        TextView txtVazia = findViewById(R.id.txt_lista_vazia_agenda_de_evento);

        AgendaDeEventoAdapter adaptador = new AgendaDeEventoAdapter(this, agendaDeEventosFiltrados);
        listView.setAdapter(adaptador);

        ListView listaAgendaEventos = findViewById(R.id.lista_agenda_de_evento);
        if (listaAgendaEventos.getAdapter() == null || listaAgendaEventos.getAdapter().getCount() == 0) {
            txtVazia.setVisibility(View.VISIBLE);
            listaAgendaEventos.setVisibility(View.GONE);
        } else {
            txtVazia.setVisibility(View.GONE);
            listaAgendaEventos.setVisibility(View.VISIBLE);
        }

        if(adaptador.getCount() != 0)
        {
            registerForContextMenu(listView);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaAgendaDeEventoActivity.this);
            builder.setTitle("Mensagem");
            builder.setMessage("Sua lista de eventos está vazia. Deseja adicionar um evento agora?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ListaAgendaDeEventoActivity.this, CadastroAgendaDeEventoActivity.class));
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(ListaAgendaDeEventoActivity.this, "Lista vazia, clique no + superior e monte sua lista", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AgendaDeEvento agendaDeEvento = agendaDeEventosFiltrados.get(i);
                AlertDialog dialog = new AlertDialog.Builder(ListaAgendaDeEventoActivity.this)
                        .setTitle("Escolha uma Opção")
                        .setItems(new CharSequence[]{"Detalhar Evento", "Atualizar", "Excluir"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int choice) {

                                if (choice == 0) {
                                    // Opção "Detalhar item" selecionada, inicie a atividade de detalhes
                                    Intent intent = new Intent(ListaAgendaDeEventoActivity.this, DetalhesAgendaDeEventoActivity.class);
                                    intent.putExtra("agendaDeEvento", agendaDeEvento);
                                    startActivity(intent);
                                }
                                else if (choice == 1) {
                                    // Opção "Atualizar" selecionada, redirecione para a tela de edição
                                    Intent intent = new Intent(ListaAgendaDeEventoActivity.this, CadastroAgendaDeEventoActivity.class);
                                    intent.putExtra("agendaDeEvento", agendaDeEvento);
                                    startActivity(intent);
                                } else if (choice == 2) {
                                    // Opção "Excluir" selecionada
                                    AgendaDeEvento agendaDeEventoExcluir = agendaDeEventosFiltrados.get(i);
                                    agendaDeEventosFiltrados.remove(agendaDeEventoExcluir);
                                    agendasDeEventos.remove(agendaDeEventoExcluir);
                                    dao.excluir(agendaDeEventoExcluir);
                                    listView.invalidateViews();
                                    Toast.makeText(getApplicationContext(), "Evento excluído com sucesso", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //System.out.println("Digitou " + s);
                procurarPorDescricaoDoEventoSearch(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
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

    public void Cadastrar(MenuItem item)
    {
        startActivity(new Intent(ListaAgendaDeEventoActivity.this, CadastroAgendaDeEventoActivity.class));
    }

    private void inicializador() {
        listView = findViewById(R.id.lista_agenda_de_evento);
    }

    private void mostrarListaDeMusicas(String musicasASeremTocadas) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Músicas a serem tocadas");

        if (musicasASeremTocadas != null && !musicasASeremTocadas.isEmpty()) {
            builder.setMessage("musicasASeremTocadas");
        } else {
            builder.setMessage("Nenhuma música cadastrada para este evento.");
        }

        builder.setPositiveButton("OK", null);
        builder.create().show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListaAgendaDeEventoActivity.this, MenuInterativoActivity.class);
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