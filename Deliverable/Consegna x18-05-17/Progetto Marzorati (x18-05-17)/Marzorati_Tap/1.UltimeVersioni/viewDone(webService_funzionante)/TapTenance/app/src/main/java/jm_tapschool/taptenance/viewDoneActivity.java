package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class viewDoneActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdone);

        setTitle("Segnalazioni completate");


        ArrayList<String> arraylist = new ArrayList<>();


        CallWebServiceViewDone callWS = new CallWebServiceViewDone();

        // Richiamo il WebService e aspetto che finisca
        try {
            arraylist = callWS.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Controllo che il WS sia finito con esito positivo
        if(arraylist.size()!=0){
            // Costruisco la RecyclerView
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listViewDone);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Spesso le informazioni della segnalazione
            ArrayList<String> titoliSegnalazioni = new ArrayList<>(); // array per titoli segnalazioni
            for(int i=0;i<arraylist.size();i++) {
                String infoSegnalazione[] = arraylist.get(i).split("~");
                String titoloSegnalazione = infoSegnalazione[0];
                titoliSegnalazioni.add(titoloSegnalazione);
            }

            // Passo i valori alla RecyclerView
            adapter = new MyRecyclerViewAdapter(this, titoliSegnalazioni);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            AlertDialog.Builder dialogErrore = new AlertDialog.Builder(viewDoneActivity.this);
            dialogErrore.setMessage("Errore!")
                    .setNegativeButton("Riprova", null)
                    .create()
                    .show();
        }
    }

    @Override
    public void onItemClick(View view, int position){


Intent open= new Intent(viewDoneActivity.this, changeStatusActivity.class);
        startActivity(open);

    }

}