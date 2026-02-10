package com.example.tienda.model

data class Producto(
    val id: Int,
    val nombre: String,
    val marca: String,
    val precio: Double,
    val stock: Int,
    val activo: Boolean
)