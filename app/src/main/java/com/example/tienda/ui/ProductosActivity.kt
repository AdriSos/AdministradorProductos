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
                // Un log para saber que la función sí se ejecutó
                android.util.Log.d("TIENDA_DEBUG", "Iniciando petición a la API...")

                val lista = RetrofitClient.instancia.obtenerProductos()

                if (lista.isEmpty()) {
                    Toast.makeText(this@ProductosActivity, "La lista llegó vacía desde el servidor", Toast.LENGTH_SHORT).show()
                } else {
                    rvProductos.adapter = ProductoAdapter(lista)
                    android.util.Log.d("TIENDA_DEBUG", "¡Éxito! Productos cargados: ${lista.size}")
                }
            } catch (e: Exception) {
                // Este log es VITAL. Nos dirá si es un Timeout, un error 500 o un error de red
                android.util.Log.e("TIENDA_DEBUG", "ERROR DE RED DETECTADO: ${e.message}")
                Toast.makeText(this@ProductosActivity, "Fallo al conectar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}