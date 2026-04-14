package com.example.restauranteapp.modelo

class CuentaMesa {

    var aceptaPropina: Boolean = true

    fun calcularPropina(total: Int): Int {
        return if (aceptaPropina) (total * 0.1).toInt() else 0
    }
}