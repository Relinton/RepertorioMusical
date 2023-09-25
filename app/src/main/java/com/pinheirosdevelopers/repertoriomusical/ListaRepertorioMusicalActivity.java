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
import android.widget.Toast;

import com.pinheirosdevelopers.repertoriomusical.adapter.RepertorioMusicalAdapter;
import com.pinheirosdevelopers.repertoriomusical.conexao.RepertorioMusicalDAO;
import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;

import java.util.ArrayList;
import java.util.List;

public class ListaRepertorioMusicalActivity extends AppCompatActivity {

    private ListView listView;
    private RepertorioMusicalDAO dao;
    private List<RepertorioMusical> repertoriosMusicais;
    private List<RepertorioMusical>  repertoriosMusicaisFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_repertorio_musical);

        inicializador();
        dao = new RepertorioMusicalDAO(this);
        repertoriosMusicais = dao.obterTodos();
        repertoriosMusicaisFiltrados.addAll(repertoriosMusicais);

        RepertorioMusicalAdapter adaptador = new RepertorioMusicalAdapter(this, repertoriosMusicaisFiltrados);
        listView.setAdapter(adaptador);

        if(adaptador.getCount() != 0)
        {
            registerForContextMenu(listView);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaRepertorioMusicalActivity.this);
            builder.setTitle("Mensagem");
            builder.setMessage("Sua lista de repertórios musicais está vazia. Deseja adicionar uma música agora?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ListaRepertorioMusicalActivity.this, CadastroRepertorioMusicalActivity.class));
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(ListaRepertorioMusicalActivity.this, "Lista vazia, clique no + superior e monte sua lista", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView <?> adapter, View view, int posicao, long id){
                String nomeDaMusica = repertoriosMusicaisFiltrados.get(posicao).getNomeDaMusica();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                RepertorioMusical repositorioMusical = repertoriosMusicaisFiltrados.get(i);
                AlertDialog dialog = new AlertDialog.Builder(ListaRepertorioMusicalActivity.this)
                        .setTitle("Escolha uma Opção")
                        .setItems(new CharSequence[]{"Atualizar", "Excluir"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int choice) {
                                if (choice == 0) {
                                    // Opção "Atualizar" selecionada, redirecione para a tela de edição
                                    Intent intent = new Intent(ListaRepertorioMusicalActivity.this, CadastroRepertorioMusicalActivity.class);
                                    intent.putExtra("repertorioMusical", repositorioMusical);
                                    startActivity(intent);
                                } else if (choice == 1) {
                                    // Opção "Excluir" selecionada
                                    RepertorioMusical repositorioMusicalExcluir = repertoriosMusicaisFiltrados.get(i);
                                    repertoriosMusicaisFiltrados.remove(repositorioMusicalExcluir);
                                    repertoriosMusicais.remove(repositorioMusicalExcluir);
                                    dao.excluir(repositorioMusicalExcluir);
                                    listView.invalidateViews();
                                    Toast.makeText(getApplicationContext(), "Música excluída com sucesso", Toast.LENGTH_SHORT).show();
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
                procurarPorEnderecoSearch(s);
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

    public void procurarPorEnderecoSearch(String nomeDaMusica)
    {
        repertoriosMusicaisFiltrados.clear();
        for (RepertorioMusical e : repertoriosMusicais)
        {
            if (e.getNomeDaMusica().toLowerCase().contains(nomeDaMusica.toLowerCase()))
            {
                repertoriosMusicaisFiltrados.add(e);
            }
        }
        listView.invalidateViews();
    }

    public void Cadastrar(MenuItem item)
    {
        startActivity(new Intent(ListaRepertorioMusicalActivity.this, CadastroRepertorioMusicalActivity.class));
    }

    private void inicializador() {
        listView = findViewById(R.id.lista_repertorio_musical);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        repertoriosMusicais = dao.obterTodos();
        repertoriosMusicaisFiltrados.clear();
        repertoriosMusicaisFiltrados.addAll(repertoriosMusicais);
        listView.invalidateViews();
    }
}