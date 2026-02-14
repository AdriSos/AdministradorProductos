package com.example.tienda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.ui.ProductosActivity
import com.example.tienda.ui.RegistroActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val tvRegistro = findViewById<TextView>(R.id.tvRegistro)

        btnIngresar.setOnClickListener {
            val user = etUsuario.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            // CAMBIO: Ahora permite entrar si los campos no están vacíos
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                val intent = Intent(this, ProductosActivity::class.java)
                intent.putExtra("NOMBRE_USUARIO", user)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}