package com.example.ebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);

        EditText restaurantNameEditText = findViewById(R.id.restaurantNameEditText);
        EditText personalNameEditText = findViewById(R.id.personalNameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "¡Gracias! Nos pondremos en contacto a la brevedad", Toast.LENGTH_LONG).show();
                restaurantNameEditText.setText("");
                personalNameEditText.setText("");
                emailEditText.setText("");
            }
        });
    }
}