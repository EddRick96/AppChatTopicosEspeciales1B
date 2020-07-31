package com.example.chatfriendly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //Variables de entrada
    private EditText name, lastName, email, pwd, confPwd;
    private Button signUpBtn;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Instanciar Variables entrada con el frame xml
        name = findViewById(R.id.nameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        email = findViewById(R.id.emailRegEditText);
        pwd = findViewById(R.id.pwdRegEditText);
        confPwd = findViewById(R.id.confirmPwdEditText);
        signUpBtn = findViewById(R.id.signUpButton);

        setupRegister();
    }

    private void setupRegister(){
        setTitle("Registrar Usuario - FoxChat");

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                testText( name.toString(),
                        lastName.toString(),
                        email.toString(),
                        pwd.toString(),
                        confPwd.toString());

                if(pwd.length() < 8){
                    pwd.setError("Por favor, ingrese una clave más larga.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email.toString(), pwd.toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Usuario creado exitósamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "No se pudo crear al usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    private void testText(String n,String a, String m, String p, String c){
        if(TextUtils.isEmpty(n)){
            name.setError("Por favor, ingrese su nombre.");
            return;
        }

        if(TextUtils.isEmpty(a)){
            lastName.setError("Por favor, ingrese su apellido.");
            return;
        }

        if(TextUtils.isEmpty(m)){
            email.setError("Por favor, ingrese su correo.");
            return;
        }

        if(TextUtils.isEmpty(p)){
            pwd.setError("Por favor, ingrese su clave.");
            return;
        }

        if(TextUtils.isEmpty(c)){
            confPwd.setError("Por favor, ingrese su confirma de clave.");
            return;
        }
    }
}