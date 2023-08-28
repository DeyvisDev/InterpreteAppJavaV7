package com.example.interpreteappjavav7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView registrate;
    EditText correoUsuario;
    EditText contrasena;
    Button btnLogin;
    FirebaseAuth mAuth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        correoUsuario = findViewById(R.id.correoUsuario);
        contrasena = findViewById(R.id.contrasena);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //ir a registro
        registrate = findViewById(R.id.registrate);
        registrate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        //fin de ir a registro
    }

    private void login() {
        String emailLogin = correoUsuario.getText().toString();
        String passwordLogin = contrasena.getText().toString();

        mAuth.signInWithEmailAndPassword(emailLogin,passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this, InicioActivity.class);
                    i.putExtra("email", emailLogin);
                    i.putExtra("pass", passwordLogin);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"El correo o la contraseña son incorrectas",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}