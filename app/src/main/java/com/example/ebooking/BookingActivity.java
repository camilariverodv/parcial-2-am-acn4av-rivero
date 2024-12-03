package com.example.ebooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    private List<Booking> bookings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Simulo reservas
        bookings.add(new Booking("Camila Rivero", "7/12/2023", "21:00 hs", "4"));
        bookings.add(new Booking("Juan Pérez", "8/12/2023", "19:00 hs", "2"));
        bookings.add(new Booking("María Gómez", "9/12/2023", "20:00 hs", "5"));
        bookings.add(new Booking("Luis Martínez", "10/12/2023", "22:00 hs", "3"));
        bookings.add(new Booking("Ana López", "11/12/2023", "18:00 hs", "6"));
        bookings.add(new Booking("Pedro Ruiz", "12/12/2023", "21:30 hs", "4"));

        // Voy a construir mis cards dentro de este contenedor de activity_booking
        LinearLayout bookingListContainer = findViewById(R.id.bookingListContainer);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (Booking booking : bookings) {
            View bookingCard = inflater.inflate(R.layout.booking_card, bookingListContainer, false);
            TextView bookingDetailsTextView = bookingCard.findViewById(R.id.bookingDetailsTextView);
            String bookingDetails = "Reserva a nombre de: " + booking.getCliente() +
                    "\nFecha: " + booking.getFecha() +
                    "\nHora: " + booking.getHora() +
                    "\nCantidad de comensales: " + booking.getCantidadPersonas();
            bookingDetailsTextView.setText(bookingDetails);

            // Agrego el bookingCard al contenedor
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