package com.mariaamigateam.mariaamiga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mariaamigateam.mariaamiga.model.Usuario;

import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity {

    Button boton;
    Button register;
    FirebaseAuth mAuth;
    TextView email;
    TextView password;
    TextView password_verify;
    TextView login;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        email = (TextView) findViewById(R.id.et_correo);
        password = (TextView) findViewById(R.id.et_pass);
        password_verify = (TextView) findViewById(R.id.et_vpass);
        /*boton = findViewById(R.id.button1);
        boton.setOnClickListener(openActivity);*/
        login = (TextView) findViewById(R.id.textView);
        login.setOnClickListener(loginActivity_bt);
        register = findViewById(R.id.bt_register);
        register.setOnClickListener(registrar);
    }
    private View.OnClickListener loginActivity_bt = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(registerActivity.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private View.OnClickListener registrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean mail = true,pass=true,vpass=true;
            if(!password.getText().toString().equals(password_verify.getText().toString())){
                password.setError("Contrase??as diferentes.");
                password_verify.setError("Contrase??as diferentes.");
                vpass=false;
                pass=false;
            }
            if(password.getTextSize()<8){
                if(password.getText().length()==0){
                    password.setError("Ingrese una contrase??a");
                    pass=false;
                }
                else{
                    password.setError("La contrase??a debe tener mas de 8 caracteres.");
                    pass=false;
                }
            }
            if(password_verify.getText().length()==0){
                password_verify.setError("Repita su contrase??a.");
                vpass=false;
            }
            if(!validarEmail(email.getText().toString())){
                email.setError("Correo invalido.");
                mail = false;
            }
            if(email.getText().length()==0){
                email.setError("Ingrese un correo electronico.");
                mail=false;
            }
            if(mail&&pass&&vpass){
                String correo = email.getText().toString();
                String contrase??a = password.getText().toString();
                mAuth.createUserWithEmailAndPassword(correo,contrase??a)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()) {
                                            Usuario nuevoUsuario = new Usuario();
                                            db.collection("usuarios").document(correo.toLowerCase()).set(nuevoUsuario);
                                            Toast.makeText(registerActivity.this, "??Registro Exitoso!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(registerActivity.this, completeRegisterActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(
                                                            getApplicationContext(),
                                                            "Error al registrar!",
                                                            Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    }
                                }
                        );
            }
        }
    };
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

}