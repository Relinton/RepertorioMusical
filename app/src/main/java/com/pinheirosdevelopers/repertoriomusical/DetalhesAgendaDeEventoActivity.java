package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

public class DetalhesAgendaDeEventoActivity extends AppCompatActivity {

    private AgendaDeEvento agendaDeEvento = null;
    private TextView descricaoDoEvento, enderecoDoEvento, dataDoEvento, musicasDoEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_agenda_de_evento);

        try {
            this.getSupportActionBar().show();
        }
        catch (NullPointerException e) {
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descricaoDoEvento = findViewById(R.id.txt_detalhes_descricao);
        enderecoDoEvento = findViewById(R.id.txt_detalhes_endereco);
        dataDoEvento = findViewById(R.id.txt_detalhes_data);
        musicasDoEvento = findViewById(R.id.txt_detalhes_musicas);

        Intent it = getIntent();
        if (it.hasExtra("agendaDeEvento"))
        {
            agendaDeEvento = (AgendaDeEvento) it.getSerializableExtra("agendaDeEvento");
            descricaoDoEvento.setText(agendaDeEvento.getDescricaoDoEvento());
            enderecoDoEvento.setText(agendaDeEvento.getEnderecoDoEvento());
            dataDoEvento.setText(agendaDeEvento.getDataDoEvento() + " " + agendaDeEvento.getHoraDoEvento());
            musicasDoEvento.setText(agendaDeEvento.getMusicasASeremTocadas());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetalhesAgendaDeEventoActivity.this, ListaAgendaDeEventoActivity.class);
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