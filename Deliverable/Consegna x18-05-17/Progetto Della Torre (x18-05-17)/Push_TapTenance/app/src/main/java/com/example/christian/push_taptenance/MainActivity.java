package com.example.christian.push_taptenance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Segnalazione NewSegn = new Segnalazione();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSegnalazione = (Button) findViewById(R.id.button_segnalazione);
        btnSegnalazione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText TitoloSegnalazione = (EditText)findViewById(R.id.editText_titolo);
                String Titolo = TitoloSegnalazione.getText().toString();

                final EditText DescrizioneSegnalazione = (EditText)findViewById(R.id.editText_desc);
                String Descrizione = DescrizioneSegnalazione.getText().toString();

                final EditText LottoSegnalazione = (EditText)findViewById(R.id.editText_lotto);
                String Lotto = LottoSegnalazione.getText().toString();

                final EditText UtenteSegnalazione = (EditText)findViewById(R.id.editText2_utente);
                String Utente = UtenteSegnalazione.getText().toString();

                // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
                final CallWebService callWS = new CallWebService();


                // richiamo il thread di tipo AsyncTask e aspetto che finisca
                Object result = null;
                try {
                    result = callWS.execute(CallWebService.METHOD_SEGNALAZIONE_PUSH, Titolo, Descrizione, Lotto, Utente).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
