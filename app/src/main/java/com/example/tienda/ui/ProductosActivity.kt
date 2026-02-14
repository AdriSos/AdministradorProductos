package com.example.tienda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda.R
import com.example.tienda.adapter.ProductoAdapter
import com.example.tienda.network.RetrofitClient
import kotlinx.coroutines.launch

class ProductosActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val tvNombre = findViewById<TextView>(R.id.tvNombreCliente)
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        rvProductos = findViewById(R.id.rvProductos)

        tvNombre.text = "Hola, ${intent.getStringExtra("NOMBRE_USUARIO")}"
        rvProductos.layoutManager = LinearLayoutManager(this)

        ivLogo.setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }

        cargarDesdeAPI()
    }

    private fun cargarDesdeAPI() {
        lifecycleScope.launch {
            try {
                // Llama a https://api-tienda-9vfy.onrender.com/productos
                val lista = RetrofitClient.instancia.obtenerProductos()
                rvProductos.adapter = ProductoAdapter(lista)
            } catch (e: Exception) {
                Toast.makeText(this@ProductosActivity, "Error de red: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}