package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setText("Non hai un account?" + System.getProperty ("line.separator") + "Registrati adesso!");

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
                final CallWebServiceLogin callWS = new CallWebServiceLogin();

                // richiamo il thread di tipo AsyncTask e aspetto che finisca
                Object result = null;
                try {
                    result = callWS.execute(CallWebServiceLogin.METHOD_NAME_LOGIN, username, password).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                String IDutente = result.toString();

                if(!IDutente.equals("-1")){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("userid",IDutente);
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