package com.jesufertez.elbajnmstico.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesufertez.elbajnmstico.data.ProductoRepository
import com.jesufertez.elbajnmstico.data.model.ProductosResponse
import kotlinx.coroutines.launch

class ProductosViewModel(private val productoRepository: ProductoRepository): ViewModel() {
    private val mutableState = MutableLiveData<ProductosResponse>()
    fun state():LiveData<ProductosResponse> = mutableState

    fun getProductos(){
        viewModelScope.launch {
            val productos = productoRepository.getProducto()
            handleState(productos)
        }
    }

    private fun handleState(productos: retrofit2.Response<ProductosResponse>) {
   if(productos.isSuccessful){
       handleBody(productos.body())
   }
    }

    private fun handleBody(body: ProductosResponse?) {
    body?.let { safeBody ->
        mutableState.postValue(safeBody)
    }
    }


}