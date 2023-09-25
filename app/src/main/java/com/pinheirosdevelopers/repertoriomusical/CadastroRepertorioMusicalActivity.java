package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pinheirosdevelopers.repertoriomusical.conexao.RepertorioMusicalDAO;
import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;

public class CadastroRepertorioMusicalActivity extends AppCompatActivity {

    //private InterstitialAd mInterstitialAd;
    private EditText nomeDaMusica, nomeDoArtistaBanda, tomMusical;
    private Button btnSalvar;
    private RepertorioMusicalDAO dao;
    private RepertorioMusical repertorioMusical = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_repertorio_musical);

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

        nomeDaMusica = findViewById(R.id.cardNomeMusica);
        nomeDoArtistaBanda = findViewById(R.id.cadArtistaBanda);
        tomMusical = findViewById(R.id.cadNotaPessoalTom);
        btnSalvar = findViewById(R.id.btnSalvarRepertorioMusical);
        dao = new RepertorioMusicalDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("repertorioMusical"))
        {
            repertorioMusical = (RepertorioMusical) it.getSerializableExtra("repertorioMusical");
            nomeDaMusica.setText(repertorioMusical.getNomeDaMusica());
            nomeDoArtistaBanda.setText(repertorioMusical.getNomeDoArtistaBanda());
            tomMusical.setText(repertorioMusical.getTomMusical().toString());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnSalvar.setEnabled(false);
                if (nomeDaMusica.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroRepertorioMusicalActivity.this, "Digite o nome da música", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nomeDoArtistaBanda.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroRepertorioMusicalActivity.this, "Digite o nome do artista/banda", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tomMusical.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroRepertorioMusicalActivity.this, "Digite o tom musical", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (repertorioMusical == null) {
                    repertorioMusical = new RepertorioMusical();
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
                    repertorioMusical.setNomeDaMusica(nomeDaMusica.getText().toString());
                    repertorioMusical.setNomeDoArtistaBanda(nomeDoArtistaBanda.getText().toString());
                    repertorioMusical.setTomMusical(tomMusical.getText().toString());
                    long id = dao.inserir(repertorioMusical);
                    Toast.makeText(CadastroRepertorioMusicalActivity.this, "A música " +nomeDaMusica.getText().toString() + " foi inserida na lista", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroRepertorioMusicalActivity.this, ListaRepertorioMusicalActivity.class));
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
                    repertorioMusical.setNomeDaMusica(nomeDaMusica.getText().toString());
                    repertorioMusical.setNomeDoArtistaBanda(nomeDoArtistaBanda.getText().toString());
                    repertorioMusical.setTomMusical(tomMusical.getText().toString());
                    Toast.makeText(CadastroRepertorioMusicalActivity.this, "A música " +nomeDaMusica.getText().toString() + " foi atualizada com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroRepertorioMusicalActivity.this, ListaRepertorioMusicalActivity.class));
                    dao.atualizar(repertorioMusical);
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
        Intent intent = new Intent(CadastroRepertorioMusicalActivity.this, MenuInterativoActivity.class);
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