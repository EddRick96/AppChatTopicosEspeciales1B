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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AuthActivity extends AppCompatActivity {

    private EditText autMail;
    private EditText autPwd;
    private Button supBtn, loginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        // Obtain the FirebaseAnalytics instance.
        //Firebase Analytics
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        /*bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");*/
        bundle.putString("message","Integración de Firebase completa");
        mFirebaseAnalytics.logEvent("InitScreen", bundle); //s: FirebaseAnalytics.Event.SELECT_CONTENT

        fAuth = FirebaseAuth.getInstance();

        autMail = findViewById(R.id.emailEditText);
        autPwd = findViewById(R.id.passwordEditText);
        supBtn = findViewById(R.id.signUpButton);
        loginBtn = findViewById(R.id.logInButton);

        //Setup
        setup();
;    }

    private void setup()
    {
        setTitle("Autenticación");

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), ChatActivity.class));
            finish();
        }

        final String mail = autMail.getText().toString().trim();
        final String pwd = autPwd.getText().toString().trim();

        supBtn.setOnClickListener(new View.OnClickListener() {
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
                            startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                        }else{
                            Toast.makeText(AuthActivity.this, "No se pudo crear al usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    testText(mail, pwd);

                    fAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AuthActivity.this, "Usuario accedió exitósamente", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                            }else{
                                Toast.makeText(AuthActivity.this, "No se pudo acceder al usuario.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        );


    }



    private void testText(String m, String p){
        if(TextUtils.isEmpty(m)){
            autMail.setError("Por favor, ingrese su correo.");
            return;
        }

        if(TextUtils.isEmpty(p)){
            autPwd.setError("Por favor, ingrese su clave.");
            return;
        }
    }
}