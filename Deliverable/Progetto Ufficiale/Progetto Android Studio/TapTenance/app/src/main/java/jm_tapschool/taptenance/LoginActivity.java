package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        final TextView textViewRisultato = (TextView) findViewById(R.id.tvResult);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                LoginActivity.this.finish();
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString().toString();
                final String password = etPassword.getText().toString().toString();

                // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
                CallWebService callWS = new CallWebService();

                // imposto textView per permettere al thread che comunica con il web service di visualizzare il risultato
                callWS.setTextViewRisultato(textViewRisultato);

                // richiamo il thread di tipo AsyncTask chiedendo l'esecuzione del web service
                callWS.execute(CallWebService.METHOD_NAME_LOGIN, username, password);

                // vedo se ha trovato l'utente, passo all'activity home
                int IdUtente = callWS.getIdUtente();
                if(IdUtente!=-1){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("userid",Integer.toString(IdUtente));
                    intent.putExtra("username",username);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    AlertDialog.Builder dialogErrore = new AlertDialog.Builder(LoginActivity.this);
                    dialogErrore.setMessage("Errore nel Login")
                            .setNegativeButton("Riprova", null)
                            .create()
                            .show();
                }
            }
        });
    }
}