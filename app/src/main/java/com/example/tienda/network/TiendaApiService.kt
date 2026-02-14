package com.example.tienda.network

import com.example.tienda.model.Producto
import retrofit2.http.GET

interface TiendaApiService {

    // Según el código de tu compañero: @app.get("/productos")
    @GET("productos")
    suspend fun obtenerProductos(): List<Producto>

    // Nota: Si tu compañero aún no crea los de Login o Ventas en FastAPI,
    // los agregaremos aquí después. Por ahora, conectemos los productos.
}