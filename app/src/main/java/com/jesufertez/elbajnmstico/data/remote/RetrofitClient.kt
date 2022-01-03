package com.jesufertez.elbajnmstico.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private  const val URL_BASE = "https://mocki.io/v1/"

    fun getProducto(): ProductosApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProductosApi::class.java)

    }
}
