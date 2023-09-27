package com.pinheirosdevelopers.repertoriomusical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pinheirosdevelopers.repertoriomusical.conexao.EnsaioDAO;
import com.pinheirosdevelopers.repertoriomusical.model.Ensaio;
import com.santalu.maskara.widget.MaskEditText;

public class CadastroEnsaioActivity extends AppCompatActivity {

    //private InterstitialAd mInterstitialAd;
    private EditText descricaoDoEnsaio, enderecoDoEnsaio, musicasDoEnsaio;
    private MaskEditText dataDoEnsaio, horaDoEnsaio;
    private Button btnSalvar;
    private EnsaioDAO dao;
    private Ensaio ensaio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ensaio);

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

        descricaoDoEnsaio = findViewById(R.id.cardDescricaoEnsaio);
        enderecoDoEnsaio = findViewById(R.id.cadEnderecoEnsaio);
        dataDoEnsaio = findViewById(R.id.cadDataEnsaio);
        horaDoEnsaio = findViewById(R.id.cadHoraEnsaio);
        musicasDoEnsaio = findViewById(R.id.cadMusicasASeremTocadasEnsaio);
        btnSalvar = findViewById(R.id.btnSalvarEnsaio);
        dao = new EnsaioDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("ensaio"))
        {
            ensaio = (Ensaio) it.getSerializableExtra("ensaio");
            descricaoDoEnsaio.setText(ensaio.getDescricaoDoEnsaio());
            enderecoDoEnsaio.setText(ensaio.getEnderecoDoEnsaio());
            dataDoEnsaio.setText(ensaio.getDataDoEnsaio().toString());
            horaDoEnsaio.setText(ensaio.getHoraDoEnsaio());
            musicasDoEnsaio.setText(ensaio.getMusicasASeremTocadas().toString());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //btnSalvar.setEnabled(false);
                if (descricaoDoEnsaio.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroEnsaioActivity.this, "Digite a descrição do ensaio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (enderecoDoEnsaio.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroEnsaioActivity.this, "Digite o endereço do ensaio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dataDoEnsaio.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroEnsaioActivity.this, "Digite a data do ensaio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (horaDoEnsaio.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroEnsaioActivity.this, "Digite o horário do ensaio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (musicasDoEnsaio.getText().toString().trim().equals("")) {
                    Toast.makeText(CadastroEnsaioActivity.this, "Digite as músicas do ensaio", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ensaio == null) {
                    ensaio = new Ensaio();
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
                    ensaio.setDescricaoDoEnsaio(descricaoDoEnsaio.getText().toString());
                    ensaio.setEnderecoDoEnsaio(enderecoDoEnsaio.getText().toString());
                    ensaio.setDataDoEnsaio(dataDoEnsaio.getText().toString());
                    ensaio.setHoraDoEnsaio(horaDoEnsaio.getText().toString());
                    ensaio.setMusicasASeremTocadas(musicasDoEnsaio.getText().toString());
                    long id = dao.inserir(ensaio);
                    Toast.makeText(CadastroEnsaioActivity.this, "O ensaio " + descricaoDoEnsaio.getText().toString() + " foi inserido na lista", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroEnsaioActivity.this, ListaEnsaioActivity.class));
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
                    ensaio.setDescricaoDoEnsaio(descricaoDoEnsaio.getText().toString());
                    ensaio.setEnderecoDoEnsaio(enderecoDoEnsaio.getText().toString());
                    ensaio.setDataDoEnsaio(dataDoEnsaio.getText().toString());
                    ensaio.setHoraDoEnsaio(horaDoEnsaio.getText().toString());
                    ensaio.setMusicasASeremTocadas(musicasDoEnsaio.getText().toString());
                    Toast.makeText(CadastroEnsaioActivity.this, "O ensaio " + descricaoDoEnsaio.getText().toString() + " foi atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroEnsaioActivity.this, ListaEnsaioActivity.class));
                    dao.atualizar(ensaio);
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
        Intent intent = new Intent(CadastroEnsaioActivity.this, ListaEnsaioActivity.class);
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