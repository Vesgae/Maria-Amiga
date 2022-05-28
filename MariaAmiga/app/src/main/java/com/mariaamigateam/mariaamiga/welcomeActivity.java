package com.mariaamigateam.mariaamiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button iniciar = findViewById(R.id.iniciarBT);
        Button registro = findViewById(R.id.registrarBT);
        iniciar.setOnClickListener(login);
        registro.setOnClickListener(register);
    }

    private View.OnClickListener register = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(welcomeActivity.this, registerActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private View.OnClickListener login = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(welcomeActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}