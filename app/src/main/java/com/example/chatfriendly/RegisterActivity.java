package com.example.chatfriendly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    //Variables de entrada
    private EditText name, lastName, email, pwd, confPwd;
    private Button signUpBtn;

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
    }

    private void setupRegister(){
        setTitle("Registrar Usuario - FoxChat");
        /*
    * signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                testText(mail, pwd);

                if(pwd.length() < 8){
                    autPwd.setError("Por favor, ingrese una clave más larga.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AuthActivity.this, "Usuario creado exitósamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                        }else{
                            Toast.makeText(AuthActivity.this, "No se pudo crear al usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    * */
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