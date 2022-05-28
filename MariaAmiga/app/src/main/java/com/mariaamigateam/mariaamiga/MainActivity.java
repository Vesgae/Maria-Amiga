package com.mariaamigateam.mariaamiga;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private String nombre = "";
    private String telefono = "";
    private String genero = "";
    private ImageButton perfil;
    private Button frase;
    private Button agendamiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String idDco = document.getId().toString();
                                if (user.getEmail().equals(idDco)){
                                    nombre = document.getData().get("nombre").toString();
                                    telefono = new String(document.getData().get("telefono").toString());
                                    genero = new String(document.getData().get("genero").toString());
                                    break;
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        TextView bienvenida = findViewById(R.id.bienvenidaText);
        bienvenida.setText("Sea bienvenidx "+nombre);
        perfil = findViewById(R.id.editProfileBT);
        perfil.setOnClickListener(verPerfil);
        agendamiento = findViewById(R.id.agendar_BT);
        frase = findViewById((R.id.frase_BT));
        agendamiento.setOnClickListener(agendamientoBT);
        frase.setOnClickListener(fraseBT);

    }
    private View.OnClickListener verPerfil = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, profileActivity.class);
            intent.putExtra("nombre",nombre);
            intent.putExtra("tel",telefono);
            intent.putExtra("genero",genero);
            startActivity(intent);
            onPause();
        }
    };
    private View.OnClickListener agendamientoBT = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, appointmentsActivity.class);
            startActivity(intent);
            onPause();
        }
    };
    private View.OnClickListener fraseBT = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, phraseActivity.class);
            startActivity(intent);
            onPause();
        }
    };
}