package com.example.tienda.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.R

class ProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Referencia al TextView del nombre del cliente en el Toolbar
        val tvNombreCliente = findViewById<TextView>(R.id.tvNombreCliente)

        // Por ahora lo pondremos manual, luego vendrá de la API
        val nombreUsuarioLogueado = "Adrián"
        tvNombreCliente.text = "Hola, $nombreUsuarioLogueado"
    }
}