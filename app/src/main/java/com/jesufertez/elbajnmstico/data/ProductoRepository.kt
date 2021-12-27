package com.jesufertez.elbajnmstico.data

import com.jesufertez.elbajnmstico.data.remote.ProductosApi

class ProductoRepository (
    private val productoApi : ProductosApi)
{
    suspend fun getProducto()= productoApi.obtenerProducto()
}