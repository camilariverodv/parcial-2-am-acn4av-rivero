package com.example.ebooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

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
                if (restaurantNameEditText.getText().toString().isEmpty() ||
                        personalNameEditText.getText().toString().isEmpty() ||
                        emailEditText.getText().toString().isEmpty()) {
                    Toast.makeText(ContactActivity.this, "Debe completar todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactActivity.this, "¡Gracias! Nos pondremos en contacto a la brevedad",
                            Toast.LENGTH_LONG).show();
                    restaurantNameEditText.setText("");
                    personalNameEditText.setText("");
                    emailEditText.setText("");
                }
            }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }
}
