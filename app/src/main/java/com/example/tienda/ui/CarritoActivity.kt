package com.example.tienda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.R
import com.example.tienda.adapter.CarritoAdapter
import com.example.tienda.model.CarritoManager

class CarritoActivity : AppCompatActivity() {

    private lateinit var tvTotal: TextView
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        val tvNombreCliente = findViewById<TextView>(R.id.tvNombreCliente)
        tvNombreCliente.text = "Confirmar Compra"

        tvTotal = findViewById(R.id.tvTotalCarrito)
        val rvCarrito = findViewById<RecyclerView>(R.id.rvCarrito)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizarCompra)

        adapter = CarritoAdapter(CarritoManager.obtenerItems()) {
            actualizarTotal()
        }
        rvCarrito.layoutManager = LinearLayoutManager(this)
        rvCarrito.adapter = adapter

        actualizarTotal()

        btnFinalizar.setOnClickListener {
            if (CarritoManager.obtenerItems().isNotEmpty()) {
                val totalFinal = CarritoManager.obtenerTotal()

                // Abrimos el Ticket pasando el total
                val intent = Intent(this, TicketActivity::class.java)
                intent.putExtra("TOTAL_COMPRA", totalFinal)
                startActivity(intent)

                // Limpiamos el carrito para una nueva compra
                CarritoManager.limpiarCarrito()
                finish()
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actualizarTotal() {
        tvTotal.text = "$${String.format("%.2f", CarritoManager.obtenerTotal())}"
    }
}