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

import com.pinheirosdevelopers.repertoriomusical.adapter.EnsaioAdapter;
import com.pinheirosdevelopers.repertoriomusical.conexao.EnsaioDAO;
import com.pinheirosdevelopers.repertoriomusical.model.Ensaio;

import java.util.ArrayList;
import java.util.List;

public class ListaEnsaioActivity extends AppCompatActivity {

    private ListView listView;
    private EnsaioDAO dao;
    private List<Ensaio> ensaios;
    private List<Ensaio> ensaiosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ensaio);

        try {
            this.getSupportActionBar().show();
        }
        catch (NullPointerException e) {
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializador();
        dao = new EnsaioDAO(this);
        ensaios = dao.obterTodos();
        ensaiosFiltrados.addAll(ensaios);
        TextView txtVazia = findViewById(R.id.txt_lista_vazia_ensaio);

        EnsaioAdapter adaptador = new EnsaioAdapter(this, ensaiosFiltrados);
        listView.setAdapter(adaptador);

        ListView listaEnsaio = findViewById(R.id.lista_ensaio);
        if (listaEnsaio.getAdapter() == null || listaEnsaio.getAdapter().getCount() == 0) {
            txtVazia.setVisibility(View.VISIBLE);
            listaEnsaio.setVisibility(View.GONE);
        } else {
            txtVazia.setVisibility(View.GONE);
            listaEnsaio.setVisibility(View.VISIBLE);
        }

        if(adaptador.getCount() != 0)
        {
            registerForContextMenu(listView);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListaEnsaioActivity.this);
            builder.setTitle("Mensagem");
            builder.setMessage("Sua lista de ensaios está vazia. Deseja adicionar um ensaio agora?");
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ListaEnsaioActivity.this, CadastroEnsaioActivity.class));
                }
            });
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(ListaEnsaioActivity.this, "Lista vazia, clique no + superior e monte sua lista", Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ensaio ensaio = ensaiosFiltrados.get(i);
                AlertDialog dialog = new AlertDialog.Builder(ListaEnsaioActivity.this)
                        .setTitle("Escolha uma Opção")
                        .setItems(new CharSequence[]{"Detalhar Ensaio", "Atualizar", "Excluir"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int choice) {

                                if (choice == 0) {
                                    // Opção "Detalhar item" selecionada, inicie a atividade de detalhes
                                    Intent intent = new Intent(ListaEnsaioActivity.this, DetalhesEnsaioActivity.class);
                                    intent.putExtra("ensaio", ensaio);
                                    startActivity(intent);
                                }
                                else if (choice == 1) {
                                    // Opção "Atualizar" selecionada, redirecione para a tela de edição
                                    Intent intent = new Intent(ListaEnsaioActivity.this, CadastroEnsaioActivity.class);
                                    intent.putExtra("ensaio", ensaio);
                                    startActivity(intent);
                                } else if (choice == 2) {
                                    // Opção "Excluir" selecionada
                                    Ensaio ensaioExcluir = ensaiosFiltrados.get(i);
                                    ensaiosFiltrados.remove(ensaioExcluir);
                                    ensaios.remove(ensaioExcluir);
                                    dao.excluir(ensaioExcluir);
                                    listView.invalidateViews();
                                    Toast.makeText(getApplicationContext(), "Ensaio excluído com sucesso", Toast.LENGTH_SHORT).show();
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
                procurarPorDescricaoDoEnsaioSearch(s);
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

    public void procurarPorDescricaoDoEnsaioSearch(String descricaoDoEnsaio)
    {
        ensaiosFiltrados.clear();
        for (Ensaio e : ensaios)
        {
            if (e.getDescricaoDoEnsaio().toLowerCase().contains(descricaoDoEnsaio.toLowerCase()))
            {
                ensaiosFiltrados.add(e);
            }
        }
        listView.invalidateViews();
    }

    public void Cadastrar(MenuItem item)
    {
        startActivity(new Intent(ListaEnsaioActivity.this, CadastroEnsaioActivity.class));
    }

    private void inicializador() {
        listView = findViewById(R.id.lista_ensaio);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ensaios = dao.obterTodos();
        ensaiosFiltrados.clear();
        ensaiosFiltrados.addAll(ensaios);
        listView.invalidateViews();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListaEnsaioActivity.this, MenuInterativoActivity.class);
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