package com.example.tienda.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.R

class CarritoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        // Configurar Toolbar
        val tvNombreCliente = findViewById<TextView>(R.id.tvNombreCliente)
        tvNombreCliente.text = "Carrito de Adri"

        // Configurar Lista
        val rvCarrito = findViewById<RecyclerView>(R.id.rvCarrito)
        rvCarrito.layoutManager = LinearLayoutManager(this)

        // El adaptador del carrito lo crearemos en el siguiente paso

        val btnFinalizar = findViewById<Button>(R.id.btnFinalizarCompra)
        btnFinalizar.setOnClickListener {
            // Aquí ejecutaremos la lógica de la tabla 'ventas'
        }
    }
}