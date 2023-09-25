package com.pinheirosdevelopers.repertoriomusical.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.R;
import com.pinheirosdevelopers.repertoriomusical.model.RepertorioMusical;

import java.util.List;

public class RepertorioMusicalAdapter extends BaseAdapter {

    private List<RepertorioMusical> repertoriosMusicais;
    private Activity activity;

    public RepertorioMusicalAdapter(Activity activity, List<RepertorioMusical> repertoriosMusicais){
        this.activity = activity;
        this.repertoriosMusicais = repertoriosMusicais;
    }
    @Override
    public int getCount() {
        return repertoriosMusicais.size();
    }

    @Override
    public Object getItem(int i) {
        return repertoriosMusicais.get(i);
    }

    @Override
    public long getItemId(int i) {
        return repertoriosMusicais.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v  = activity.getLayoutInflater().inflate(R.layout.item_repertorio_musical, viewGroup, false);
        TextView nomeDaMusica = v.findViewById(R.id.txt_item_nome_da_musica);
        TextView nomeDoArtistaBanda = v.findViewById(R.id.txt_item_nome_do_artista_banda);
        TextView tomMusical = v.findViewById(R.id.txt_item_tom_musical);
        RepertorioMusical repertorioMusical = repertoriosMusicais.get(i);
        nomeDaMusica.setText(repertorioMusical.getNomeDaMusica());
        nomeDoArtistaBanda.setText(repertorioMusical.getNomeDoArtistaBanda());
        tomMusical.setText(repertorioMusical.getTomMusical());

        return v;
    }
}


