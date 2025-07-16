package com.example.ebooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingActivity extends AppCompatActivity {
    private List<Booking> bookings = new ArrayList<>();
    private LinearLayout bookingListContainer;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        final TextView bookingTitleTextView = findViewById(R.id.bookingTitleTextView);
        String userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;
        if (userId != null) {
            db.collection("users").document(userId).get().addOnSuccessListener(new com.google.android.gms.tasks.OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        String restaurantName = documentSnapshot.getString("restaurantName");
                        if (restaurantName != null && !restaurantName.isEmpty()) {
                            bookingTitleTextView.setText("Reservas de '" + restaurantName + "'");
                        } else {
                            bookingTitleTextView.setText("Reservas de tu restaurante");
                        }
                    } else {
                        bookingTitleTextView.setText("Reservas de tu restaurante");
                    }
                }
            });
        } else {
            bookingTitleTextView.setText("Reservas de tu restaurante");
        }

        ImageView navLogo = findViewById(R.id.navLogo);
        if (navLogo != null) {
            navLogo.setImageResource(R.drawable.logout);
        }

        // Simulo reservas
        bookings.add(new Booking("Camila Rivero", "7/12/2023", "21:00 hs", "4"));
        bookings.add(new Booking("Juan Pérez", "8/12/2023", "19:00 hs", "2"));
        bookings.add(new Booking("María Gómez", "9/12/2023", "20:00 hs", "5"));
        bookings.add(new Booking("Luis Martínez", "10/12/2023", "22:00 hs", "3"));
        bookings.add(new Booking("Ana López", "11/12/2023", "18:00 hs", "6"));
        bookings.add(new Booking("Pedro Ruiz", "12/12/2023", "21:30 hs", "4"));

        bookingListContainer = findViewById(R.id.bookingListContainer);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < bookings.size(); i++) {
            final int position = i;

            View bookingCard = inflater.inflate(R.layout.booking_card, bookingListContainer, false);
            TextView bookingDetailsTextView = bookingCard.findViewById(R.id.bookingDetailsTextView);
            Button cancelButton = bookingCard.findViewById(R.id.cancelButton);

            String bookingDetails = "Reserva a nombre de: " + bookings.get(i).getCliente() +
                    "\nFecha: " + bookings.get(i).getFecha() +
                    "\nHora: " + bookings.get(i).getHora() +
                    "\nCantidad de comensales: " + bookings.get(i).getCantidadPersonas();
            bookingDetailsTextView.setText(bookingDetails);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookings.remove(position);
                    bookingListContainer.removeAllViews();
                    renderBookings();
                    Toast.makeText(BookingActivity.this, "Reserva cancelada", Toast.LENGTH_SHORT).show();
                }
            });
            bookingListContainer.addView(bookingCard);
        }

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);
    }

    private void renderBookings() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < bookings.size(); i++) {
            final int position = i;
            View bookingCard = inflater.inflate(R.layout.booking_card, bookingListContainer, false);
            TextView bookingDetailsTextView = bookingCard.findViewById(R.id.bookingDetailsTextView);
            Button cancelButton = bookingCard.findViewById(R.id.cancelButton);

            String bookingDetails = "Reserva a nombre de: " + bookings.get(i).getCliente() +
                    "\nFecha: " + bookings.get(i).getFecha() +
                    "\nHora: " + bookings.get(i).getHora() +
                    "\nCantidad de comensales: " + bookings.get(i).getCantidadPersonas();
            bookingDetailsTextView.setText(bookingDetails);

            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookings.remove(position);
                    bookingListContainer.removeAllViews();
                    renderBookings();
                    Toast.makeText(BookingActivity.this, "Reserva cancelada", Toast.LENGTH_SHORT).show();
                }
            });

            bookingListContainer.addView(bookingCard);
        }
    }

    class Booking {
        private String cliente;
        private String fecha;
        private String hora;
        private String cantidadPersonas;

        public Booking(String cliente, String fecha, String hora, String cantidadPersonas) {
            this.cliente = cliente;
            this.fecha = fecha;
            this.hora = hora;
            this.cantidadPersonas = cantidadPersonas;
        }

        public String getCliente() {
            return cliente;
        }

        public String getFecha() {
            return fecha;
        }

        public String getHora() {
            return hora;
        }

        public String getCantidadPersonas() {
            return cantidadPersonas;
        }
    }
}