package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class changeStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_status);
        final Button bCambia = (Button) findViewById(R.id.buttonCambia);
        final EditText etStato = (EditText) findViewById(R.id.editTextStato);

        bCambia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stato = etStato.getText().toString();

                // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
                final CallWebServiceChangeStatus callWS = new CallWebServiceChangeStatus();

                // richiamo il thread di tipo AsyncTask e aspetto che finisca
                Object result = null;
                result=callWS.execute(CallWebServiceChangeStatus.METHOD_NAME_CHANGESTATUS, "1", stato);

            }
        });
    }


}
