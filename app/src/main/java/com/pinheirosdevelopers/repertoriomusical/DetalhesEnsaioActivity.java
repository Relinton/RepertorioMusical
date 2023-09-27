package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.model.Ensaio;

public class DetalhesEnsaioActivity extends AppCompatActivity {

    private Ensaio ensaio = null;
    private TextView descricaoDoEnsaio, enderecoDoEnsaio, dataDoEnsaio, musicasDoEnsaio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ensaio);

        try {
            this.getSupportActionBar().show();
        }
        catch (NullPointerException e) {
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descricaoDoEnsaio = findViewById(R.id.txt_detalhes_descricao_ensaio);
        enderecoDoEnsaio = findViewById(R.id.txt_detalhes_endereco_ensaio);
        dataDoEnsaio = findViewById(R.id.txt_detalhes_data_ensaio);
        musicasDoEnsaio = findViewById(R.id.txt_detalhes_musicas_ensaio);

        Intent it = getIntent();
        if (it.hasExtra("ensaio"))
        {
            ensaio = (Ensaio) it.getSerializableExtra("ensaio");
            descricaoDoEnsaio.setText(ensaio.getDescricaoDoEnsaio());
            enderecoDoEnsaio.setText(ensaio.getEnderecoDoEnsaio());
            dataDoEnsaio.setText(ensaio.getDataDoEnsaio() + " " + ensaio.getHoraDoEnsaio());
            musicasDoEnsaio.setText(ensaio.getMusicasASeremTocadas());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetalhesEnsaioActivity.this, ListaEnsaioActivity.class);
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