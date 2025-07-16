package com.example.ebooking;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import android.util.Log;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameEditText, lastNameEditText, emailEditText, passwordEditText;
    private Button registerSubmitButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEditText = findViewById(R.id.nameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerSubmitButton = findViewById(R.id.registerSubmitButton);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registerSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Debe completar todos los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(RegisterActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();

                            // Redirigir inmediatamente
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();

                            // Guardar datos en Firestore en segundo plano
                            String userId = mAuth.getCurrentUser().getUid();
                            Map<String, Object> user = new HashMap<>();
                            user.put("name", name);
                            user.put("lastName", lastName);
                            user.put("email", email);

                            db.collection("users").document(userId)
                                    .set(user)
                                    .addOnCompleteListener(task1 -> {
                                        Log.d("RegisterActivity", "Datos guardados correctamente en Firestore");
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("RegisterActivity", "Error al guardar en Firestore: " + e.getMessage());
                                    });

                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }
}
