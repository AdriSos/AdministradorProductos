package com.example.tienda.model

data class Usuario(
    val id: Int? = null,
    val user: String,
    val password: String,
    val activo: Boolean = true,
    val role: String = "CLIENTE"
)