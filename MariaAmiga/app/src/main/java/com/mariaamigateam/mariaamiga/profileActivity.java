package com.mariaamigateam.mariaamiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class profileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private TextView nombreTx;
    private TextView telefonoTx;
    private TextView generoTx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        String nombre = getIntent().getStringExtra("nombre");
        String telefono = getIntent().getStringExtra("tel");
        String genero = getIntent().getStringExtra("genero");
        Button cerrarSesion = findViewById(R.id.logoutBT);
        cerrarSesion.setOnClickListener(cerrarSesionBTF);
        Button volver = findViewById(R.id.volverBT);
        volver.setOnClickListener(volverFunct);
        nombreTx = findViewById(R.id.nombreText);
        nombreTx.setText(nombre);
        telefonoTx = findViewById(R.id.telefonoText);
        telefonoTx.setText(telefono);
        generoTx = findViewById(R.id.genero);
        generoTx.setText(genero);
    }
    private View.OnClickListener volverFunct = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private View.OnClickListener cerrarSesionBTF = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mAuth.signOut();
            Intent intent = new Intent(profileActivity.this, welcomeActivity.class);
            startActivity(intent);
            finish();
        }
    };
}