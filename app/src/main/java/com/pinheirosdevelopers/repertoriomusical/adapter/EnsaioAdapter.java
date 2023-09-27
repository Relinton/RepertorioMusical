package com.pinheirosdevelopers.repertoriomusical.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.R;
import com.pinheirosdevelopers.repertoriomusical.model.Ensaio;

import java.util.List;

public class EnsaioAdapter extends BaseAdapter {

    private List<Ensaio> ensaios;
    private Activity activity;

    public EnsaioAdapter(Activity activity, List<Ensaio> ensaios){
        this.activity = activity;
        this.ensaios = ensaios;
    }

    public EnsaioAdapter(Context context, List<Ensaio> listaDeMusicas) {
    }

    @Override
    public int getCount() {
        return ensaios.size();
    }

    @Override
    public Object getItem(int i) {
        return ensaios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ensaios.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v  = activity.getLayoutInflater().inflate(R.layout.item_ensaio, viewGroup, false);
        TextView descricaoDoEnsaio = v.findViewById(R.id.txt_item_descricao_ensaio);
        TextView enderecoDoEnsaio = v.findViewById(R.id.txt_item_endereco_ensaio);
        TextView dataDoEnsaio = v.findViewById(R.id.txt_item_data_ensaio);
        Ensaio ensaio = ensaios.get(i);
        descricaoDoEnsaio.setText(ensaio.getDescricaoDoEnsaio());
        enderecoDoEnsaio.setText(ensaio.getEnderecoDoEnsaio());
        dataDoEnsaio.setText(ensaio.getDataDoEnsaio() + " " + ensaio.getHoraDoEnsaio());

        return v;
    }
}
