package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Prendo i dati dal bundle dell'intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String msg = "Benvenuto, " + username;
        setTitle(msg);

        // variabile per memorizzazione segnalazioni
        ArrayList<String> segnalazioni = new ArrayList<>();

        // Creo oggetto web service (che lavora in un thread di tipo AsyncTask)
        final CallWebServiceHome callWS = new CallWebServiceHome();

        // Richiamo il WebService e aspetto che finisca
        try {
            segnalazioni = callWS.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Controllo che il WS sia finito con esito positivo
        if(segnalazioni.size()!=0){
            // Costruisco la RecyclerView
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.LVsegnalazioni);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Spesso le informazioni della segnalazione
            ArrayList<String> titoliSegnalazioni = new ArrayList<>(); // array per titoli segnalazioni
            for(int i=0;i<segnalazioni.size();i++) {
                String infoSegnalazione[] = segnalazioni.get(i).split("~");
                String titoloSegnalazione = infoSegnalazione[0];
                    // in pos. 0: titolo
                    // in pos. 1: descrizione
                    // in pos. 2: status
                titoliSegnalazioni.add(titoloSegnalazione);
            }

            // Passo i valori alla RecyclerView
            adapter = new MyRecyclerViewAdapter(this, titoliSegnalazioni);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            AlertDialog.Builder dialogErrore = new AlertDialog.Builder(HomeActivity.this);
            dialogErrore.setMessage("Errore!")
                    .setNegativeButton("Riprova", null)
                    .create()
                    .show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "(Parte di mia competenza terminata.)\nHai cliccato " + adapter.getItem(position) + " sulla segnalazione in posizione numero " + position, Toast.LENGTH_SHORT).show();
    }
}