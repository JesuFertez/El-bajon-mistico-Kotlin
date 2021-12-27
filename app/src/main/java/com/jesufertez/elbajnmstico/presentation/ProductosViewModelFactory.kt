package com.jesufertez.elbajnmstico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesufertez.elbajnmstico.data.ProductoRepository
import java.lang.IllegalArgumentException

class ProductosViewModelFactory(
    private val repository: ProductoRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            return ProductosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}