package jm_tapschool.taptenance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView lstLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String msg = "Benvenuto, " + username;
        setTitle(msg);
    }
}