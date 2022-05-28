package com.mariaamigateam.mariaamiga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class pleasuresActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    //Gustos
    CheckBox A;
    CheckBox B;
    CheckBox C;
    CheckBox D;
    CheckBox E;
    //DiaPerfecto
    CheckBox F;
    CheckBox G;
    CheckBox H;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pleasures);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        A = (CheckBox) findViewById(R.id.checkBox);
        B = (CheckBox) findViewById(R.id.checkBox2);
        C = (CheckBox) findViewById(R.id.checkBox3);
        D = (CheckBox) findViewById(R.id.checkBox4);
        E = (CheckBox) findViewById(R.id.checkBox5);
        //diaperfecto
        F = (CheckBox) findViewById(R.id.checkBox6);
        G = (CheckBox) findViewById(R.id.checkBox7);
        H = (CheckBox) findViewById(R.id.checkBox8);
        Button enviarDatos = findViewById(R.id.button3);
        enviarDatos.setOnClickListener(enviarDatosBT);

    }
    private View.OnClickListener enviarDatosBT = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ArrayList<String> listaGustos = new ArrayList<>();
            ArrayList<String> diaPerfecto = new ArrayList<>();
            if(A.isChecked()) listaGustos.add(A.getText().toString());
            if(B.isChecked()) listaGustos.add(B.getText().toString());
            if(C.isChecked()) listaGustos.add(C.getText().toString());
            if(D.isChecked()) listaGustos.add(D.getText().toString());
            if(E.isChecked()) listaGustos.add(E.getText().toString());
            if(F.isChecked()) diaPerfecto.add(F.getText().toString());
            if(G.isChecked()) diaPerfecto.add(G.getText().toString());
            if(H.isChecked()) diaPerfecto.add(H.getText().toString());
            db.collection("usuarios").document(currentUser.getEmail()).update("gustos",listaGustos);
            db.collection("usuarios").document(currentUser.getEmail()).update("diaPerfecto",diaPerfecto);
            db.collection("usuarios").document(currentUser.getEmail()).update("gustosCompleto",true);
            startActivity(new Intent(view.getContext(), MainActivity.class));
            finish();
        }
    };
}