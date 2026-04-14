package com.example.restauranteapp.modelo

class ItemMesa(
    val itemMenu: ItemMenu,
    var cantidad: Int
) {
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}