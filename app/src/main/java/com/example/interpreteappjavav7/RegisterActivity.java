package com.example.interpreteappjavav7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText usuarioRegistro;
    EditText correoRegistro;
    EditText contrasenaRegistro;
    EditText contrasenaRegistro2;
    Button btnRegistro;
    TextView logueate;

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuarioRegistro = findViewById(R.id.usuarioRegistro);
        correoRegistro = findViewById(R.id.correoRegistro);
        contrasenaRegistro = findViewById(R.id.contrasenaRegistro);
        contrasenaRegistro2 = findViewById(R.id.contrasenaRegistro2);
        btnRegistro = findViewById(R.id.btnRegistro);
        //instanciamos a firebases
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();


        //ir a login
        logueate = findViewById(R.id.logueate);
        logueate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        //fin de ir a login


        //regsitro con button
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String name = usuarioRegistro.getText().toString();
        String email = correoRegistro.getText().toString();
        String password = contrasenaRegistro.getText().toString();
        String password2 = contrasenaRegistro2.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty()){
            if(isEmailValid(email)){
                if(password.equals(password2)){
                    if (password.length()>=6){
                        createUser(name, email,password);


                    }else{
                        Toast.makeText(RegisterActivity.this,"La contraseña de tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this,"Las contraseñas no son iguales",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(RegisterActivity.this,"El correo no es valido",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(RegisterActivity.this,"Debe completar los campos correctamente",Toast.LENGTH_SHORT).show();
        }
    }

    private void createUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String id = mAuth.getCurrentUser().getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);
                    mFirestore.collection("Users").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                Toast.makeText(RegisterActivity.this,"El usuario se registro correctamente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"No se pudo registrar el usuario, intente de nuevo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isEmailValid(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}