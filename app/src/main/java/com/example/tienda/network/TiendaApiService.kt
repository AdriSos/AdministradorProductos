package com.example.tienda.network

import com.example.tienda.model.Producto
import retrofit2.http.GET

interface TiendaApiService {
    @GET("productos")
    suspend fun obtenerProductos(): List<Producto>
}