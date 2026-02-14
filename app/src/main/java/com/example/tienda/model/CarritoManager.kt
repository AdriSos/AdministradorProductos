package com.example.tienda.model

object CarritoManager {
    // Lista privada que contiene los productos y su cantidad
    private val items = mutableListOf<ItemCarrito>()

    // Clase interna para manejar producto + cantidad
    data class ItemCarrito(
        val producto: Producto,
        var cantidad: Int
    )

    fun agregarProducto(producto: Producto) {
        val existente = items.find { it.producto.id == producto.id }
        if (existente != null) {
            existente.cantidad++
        } else {
            items.add(ItemCarrito(producto, 1))
        }
    }

    fun obtenerItems(): List<ItemCarrito> = items

    fun eliminarProducto(productoId: Int) {
        items.removeAll { it.producto.id == productoId }
    }

    fun obtenerTotal(): Double {
        return items.sumOf { it.producto.precio * it.cantidad }
    }

    fun limpiarCarrito() {
        items.clear()
    }
}