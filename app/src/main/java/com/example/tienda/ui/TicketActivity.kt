package com.example.tienda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.R
import java.text.SimpleDateFormat
import java.util.*

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val tvFecha = findViewById<TextView>(R.id.tvFechaTicket)
        val tvTotal = findViewById<TextView>(R.id.tvTotalTicket)
        val btnVolver = findViewById<Button>(R.id.btnVolverTienda)

        // Recibimos el total desde el carrito
        val total = intent.getDoubleExtra("TOTAL_COMPRA", 0.0)
        tvTotal.text = "Total Pagado: $${String.format("%.2f", total)}"

        // Ponemos la fecha de hoy
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        tvFecha.text = "Fecha: ${sdf.format(Date())}"

        btnVolver.setOnClickListener {
            val intent = Intent(this, ProductosActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}