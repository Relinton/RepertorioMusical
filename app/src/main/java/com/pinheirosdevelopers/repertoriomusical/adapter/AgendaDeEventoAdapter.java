package com.pinheirosdevelopers.repertoriomusical.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pinheirosdevelopers.repertoriomusical.R;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

import java.util.List;

public class AgendaDeEventoAdapter extends BaseAdapter {

    private List<AgendaDeEvento> agendaDeEventos;
    private Activity activity;

    public AgendaDeEventoAdapter(Activity activity, List<AgendaDeEvento> agendaDeEventos){
        this.activity = activity;
        this.agendaDeEventos = agendaDeEventos;
    }

    public AgendaDeEventoAdapter(Context context, List<AgendaDeEvento> listaDeMusicas) {
    }

    @Override
    public int getCount() {
        return agendaDeEventos.size();
    }

    @Override
    public Object getItem(int i) {
        return agendaDeEventos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return agendaDeEventos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v  = activity.getLayoutInflater().inflate(R.layout.item_agenda_de_evento, viewGroup, false);
        TextView descricaoDoEvento = v.findViewById(R.id.txt_item_descricao);
        TextView enderecoDoEvento = v.findViewById(R.id.txt_item_endereco);
        TextView dataDoEvento = v.findViewById(R.id.txt_item_data);
        TextView horarioDoEvento = v.findViewById(R.id.txt_item_horario);
        TextView musicasASeremTocadas = v.findViewById(R.id.txt_item_musicas);
        AgendaDeEvento agendaDeEvento = agendaDeEventos.get(i);
        descricaoDoEvento.setText(agendaDeEvento.getDescricaoDoEvento());
        enderecoDoEvento.setText(agendaDeEvento.getEnderecoDoEvento());
        dataDoEvento.setText(agendaDeEvento.getDataDoEvento());
        horarioDoEvento.setText(agendaDeEvento.getHoraDoEvento());
        musicasASeremTocadas.setText(agendaDeEvento.getMusicasASeremTocadas());

        return v;
    }
}
