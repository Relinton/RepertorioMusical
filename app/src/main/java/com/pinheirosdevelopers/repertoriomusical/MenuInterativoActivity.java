package com.pinheirosdevelopers.repertoriomusical;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.pinheirosdevelopers.repertoriomusical.conexao.AgendaDeEventoDAO;
import com.pinheirosdevelopers.repertoriomusical.model.AgendaDeEvento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MenuInterativoActivity extends AppCompatActivity {

    private List<AgendaDeEvento> agendasDeEventos;
    private AgendaDeEventoDAO dao;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_interativo);

        dao = new AgendaDeEventoDAO(this);
        agendasDeEventos = dao.obterTodos();


        // Verifica se a data do evento é igual a data atual
        for (AgendaDeEvento agendaDeEvento : agendasDeEventos) {
            Calendar calendario = Calendar.getInstance();
            Date dataAtual = calendario.getTime();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            String dataEventoStr = agendaDeEvento.getDataDoEvento();

            try {
                // Tenta converter a String em um objeto Date
                Date dataEvento = formatoData.parse(dataEventoStr);

                // Compara as datas
                if (isDataHoje(dataEvento)) {
                    // A data do evento é igual à data atual, portanto, cria uma notificação
                    String channelId = "repertorio_musical_channel";
                    String channelName = "Repertório Musical";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
                        NotificationManager notificationManager = getSystemService(NotificationManager.class);
                        notificationManager.createNotificationChannel(channel);
                    }

                    @SuppressLint({"NewApi", "LocalSuppress"}) Notification.Builder builder = new Notification.Builder(this, channelId)
                            .setSmallIcon(R.drawable.logo)
                            .setContentTitle("Lembrete do Evento")
                            .setContentText("Hoje é o dia! O evento:  " + agendaDeEvento.getDescricaoDoEvento() + " será às " + agendaDeEvento.getHoraDoEvento() + "hrs")
                            .setAutoCancel(true);

                    // Exibe a notificação
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.notify(agendaDeEvento.getId(), builder.build());
                }
            } catch (java.text.ParseException e) {
                // Lida com exceção de análise se a String da data do evento estiver em um formato inválido
                e.printStackTrace();
            }
        }
    }

    public void RepertorioMusical(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaRepertorioMusicalActivity.class));
    }

    public void AgendaDeEventos(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaAgendaDeEventoActivity.class));
    }

    public void Ensaios(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaEnsaioActivity.class));
    }

    public void Notificacoes(View view) {
        startActivity(new Intent(MenuInterativoActivity.this, ListaNotificacaoActivity.class));
    }

    // Função para verificar se a data é hoje
    private boolean isDataHoje(Date data) {
        Calendar dataCalendario = Calendar.getInstance();
        dataCalendario.setTime(data);

        Calendar hojeCalendario = Calendar.getInstance();

        return dataCalendario.get(Calendar.YEAR) == hojeCalendario.get(Calendar.YEAR) &&
                dataCalendario.get(Calendar.MONTH) == hojeCalendario.get(Calendar.MONTH) &&
                dataCalendario.get(Calendar.DAY_OF_MONTH) == hojeCalendario.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Sair do aplicativo")
                .setMessage("Deseja sair do aplicativo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}