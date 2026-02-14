package com.example.tienda.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tienda.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // 1. Referencias de la UI
        val etUser = findViewById<EditText>(R.id.etNuevoUsuario)
        val etPass = findViewById<EditText>(R.id.etNuevoPassword)
        val etConfirm = findViewById<EditText>(R.id.etConfirmarPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnFinalizarRegistro)
        val tvVolver = findViewById<TextView>(R.id.tvVolverLogin)

        // 2. Acción del botón registrar
        btnRegistrar.setOnClickListener {
            val user = etUser.text.toString().trim()
            val pass = etPass.text.toString().trim()
            val confirm = etConfirm.text.toString().trim()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            } else if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí se haría la llamada POST a la API en el futuro
                Toast.makeText(this, "¡Registro exitoso para $user!", Toast.LENGTH_LONG).show()
                finish() // Cierra esta pantalla y vuelve al Login
            }
        }

        // 3. Volver al Login si ya tiene cuenta
        tvVolver.setOnClickListener {
            finish()
        }
    }
}