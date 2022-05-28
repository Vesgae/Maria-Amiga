package com.mariaamigateam.mariaamiga;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mariaamigateam.mariaamiga.model.Usuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class completeRegisterActivity extends AppCompatActivity {

    EditText nombre;
    EditText telefono;
    EditText etPlannedDate;
    private RadioGroup grupo;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        etPlannedDate = (EditText)findViewById(R.id.et_fechaCita);
        etPlannedDate.setOnClickListener(datePick);
        nombre = (EditText)findViewById(R.id.et_nombre);
        telefono = (EditText)findViewById(R.id.et_telefono);
        grupo = (RadioGroup) findViewById(R.id.grupoRadioBT);
        Button enviar = findViewById(R.id.completarRegistro);
        enviar.setOnClickListener(enviarDatos);
    }
    private View.OnClickListener datePick = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showDatePickerDialog();
        }
    };
    private View.OnClickListener enviarDatos = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre.getText().toString());
            RadioButton radioBT = (RadioButton) findViewById(grupo.getCheckedRadioButtonId());
            nuevoUsuario.setGenero(radioBT.getText().toString());
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            nuevoUsuario.setPerfilCompleto(true);
            db.collection("usuarios").document(currentUser.getEmail()).update("nombre",nuevoUsuario.getNombre());
            db.collection("usuarios").document(currentUser.getEmail()).update("telefono",nuevoUsuario.getTelefono());
            db.collection("usuarios").document(currentUser.getEmail()).update("genero",nuevoUsuario.getGenero());
            db.collection("usuarios").document(currentUser.getEmail()).update("perfilCompleto",true);
            db.collection("usuarios").document(currentUser.getEmail()).update("fechaNacimiento",nuevoUsuario.getFechaNacimiento());
            startActivity(new Intent(view.getContext(), pleasuresActivity.class));
            finish();
            try {
                nuevoUsuario.setFechaNacimiento(format.parse(etPlannedDate.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}