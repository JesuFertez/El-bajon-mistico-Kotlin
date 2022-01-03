package com.jesufertez.elbajnmstico.data.remote

import com.jesufertez.elbajnmstico.data.model.Producto
import com.jesufertez.elbajnmstico.data.model.ProductosResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductosApi {
    @GET("00cff39e-73e0-4abe-a642-fd9137eac223")
    suspend fun obtenerProducto() :Response<ProductosResponse>
}