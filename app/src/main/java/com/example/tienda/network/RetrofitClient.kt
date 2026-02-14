package com.example.tienda.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://api-tienda-9vfy.onrender.com/"

    // Agregamos un cliente con más tiempo de espera (60 segundos)
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val instancia: TiendaApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Usamos el cliente con más tiempo
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TiendaApiService::class.java)
    }
}