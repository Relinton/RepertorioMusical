package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pinheirosdevelopers.repertoriomusical.conexao.AgendaDeEventoDAO;
import com.pinheirosdevelopers.repertoriomusical.conexao.RepertorioMusicalDAO;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;
import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;
import com.santalu.maskara.widget.MaskEditText;

public class CadastroAgendaDeEventoActivity extends AppCompatActivity {

    //private InterstitialAd mInterstitialAd;
    private EditText descricaoDoEvento, enderecoDoEvento, musicasDoEvento;
    private MaskEditText dataDoEvento, horaDoEvento;
    private Button btnSalvar;
    private AgendaDeEventoDAO dao;
    private AgendaDeEvento agendaDeEvento = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agenda_de_evento);

        /*MobileAds.initialize(CadastroProdutoActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });*/

        //loadAd();

        try {
            this.getSupportActionBar().show();
        }
        catch (NullPointerException e) {
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descricaoDoEvento = findViewById(R.id.cardDescricaoEvento);
        enderecoDoEvento = findViewById(R.id.cadEnderecoEvento);
        dataDoEvento = findViewById(R.id.cadDataEvento);
        horaDoEvento = findViewById(R.id.cadHoraEvento);
        musicasDoEvento = findViewById(R.id.cadMusicasASeremTocadas);
        btnSalvar = findViewById(R.id.btnSalvarAgendaEvento);
        dao = new AgendaDeEventoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("agendaDeEvento"))
        {
            agendaDeEvento = (AgendaDeEvento) it.getSerializableExtra("agendaDeEvento");
            descricaoDoEvento.setText(agendaDeEvento.getDescricaoDoEvento());
            enderecoDoEvento.setText(agendaDeEvento.getEnderecoDoEvento());
            dataDoEvento.setText(agendaDeEvento.getDataDoEvento().toString());
            horaDoEvento.setText(agendaDeEvento.getHoraDoEvento());
            musicasDoEvento.setText(agendaDeEvento.getMusicasASeremTocadas().toString());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnSalvar.setEnabled(false);
                if (descricaoDoEvento.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "Digite a descrição do evento", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (enderecoDoEvento.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "Digite o endereço do evento", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dataDoEvento.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "Digite a data do evento", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (horaDoEvento.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "Digite o horário do evento", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (musicasDoEvento.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "Digite as músicas do evento", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (agendaDeEvento == null) {
                    agendaDeEvento = new AgendaDeEvento();
                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(CadastroProdutoActivity.this);
                                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        startActivity(new Intent(CadastroProdutoActivity.this, MainActivity.class));
                                        finish();
                                        super.onAdDismissedFullScreenContent();
                                    }
                                });
                            } else {
                                startActivity(new Intent(CadastroProdutoActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    }, 1000);*/
                    agendaDeEvento.setDescricaoDoEvento(descricaoDoEvento.getText().toString());
                    agendaDeEvento.setEnderecoDoEvento(enderecoDoEvento.getText().toString());
                    agendaDeEvento.setDataDoEvento(dataDoEvento.getText().toString());
                    agendaDeEvento.setHoraDoEvento(horaDoEvento.getText().toString());
                    agendaDeEvento.setMusicasASeremTocadas(musicasDoEvento.getText().toString());
                    long id = dao.inserir(agendaDeEvento);
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "O evento " +descricaoDoEvento.getText().toString() + " foi inserido na lista", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroAgendaDeEventoActivity.this, ListaAgendaDeEventoActivity.class));
                } else {
                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(CadastroProdutoActivity.this);
                                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        startActivity(new Intent(CadastroProdutoActivity.this, MainActivity.class));
                                        finish();
                                        super.onAdDismissedFullScreenContent();
                                    }
                                });
                            } else {
                                startActivity(new Intent(CadastroProdutoActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    }, 1000);*/
                    agendaDeEvento.setDescricaoDoEvento(descricaoDoEvento.getText().toString());
                    agendaDeEvento.setEnderecoDoEvento(enderecoDoEvento.getText().toString());
                    agendaDeEvento.setDataDoEvento(dataDoEvento.getText().toString());
                    agendaDeEvento.setHoraDoEvento(horaDoEvento.getText().toString());
                    agendaDeEvento.setMusicasASeremTocadas(musicasDoEvento.getText().toString());
                    Toast.makeText(CadastroAgendaDeEventoActivity.this, "O evento " +descricaoDoEvento.getText().toString() + " foi atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroAgendaDeEventoActivity.this, ListaAgendaDeEventoActivity.class));
                    dao.atualizar(agendaDeEvento);
                }
            }
        });

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnSalvar.setEnabled(true);
            }
        }, 4000);*/
    }

    /*private void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-8608549616868522/5535504266", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }*/

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CadastroAgendaDeEventoActivity.this, ListaAgendaDeEventoActivity.class);
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