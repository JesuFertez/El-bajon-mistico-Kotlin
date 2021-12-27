package com.jesufertez.elbajnmstico.data.remote

import com.jesufertez.elbajnmstico.data.model.Producto
import retrofit2.Response
import retrofit2.http.GET

interface ProductosApi {
    @GET("products")
    suspend fun obtenerProducto() :Response<Producto>
}