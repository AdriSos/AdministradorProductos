package com.example.tienda.model

data class VentaRequest(
    val usuario_id: Int,
    val total: Double,
    val detalles: List<VentaDetalleRequest>
)

data class VentaDetalleRequest(
    val producto_id: Int,
    val cantidad: Int,
    val precio_unitario: Double
)