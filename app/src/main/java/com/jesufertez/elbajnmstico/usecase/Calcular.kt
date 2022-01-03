package com.jesufertez.elbajnmstico.usecase

class Calcular {

    companion object{

    fun opera(subtotal: Int): Int {

        val totalDescuento: Int
        totalDescuento = if (subtotal >= 5000 && subtotal < 8000) {
            subtotal * 5 / 100
        } else if (subtotal > 8000) {
            subtotal * 10 / 100
        } else {
            0
        }
        return totalDescuento
    }

    }
}