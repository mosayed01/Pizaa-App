package com.mooncake.pizaaapp.presentation.main

import androidx.annotation.DrawableRes
import com.mooncake.pizaaapp.R

data class Pizza(
    val id: Int,
    @DrawableRes val image: Int,
    val size: PizzaSize = PizzaSize.SMALL,
    val prices: Map<PizzaSize, Int>,
    val ingredients: Set<PizzaIngredient> = emptySet(),
) {
    enum class PizzaSize(val char: Char) {
        SMALL('S'),
        MEDIUM('M'),
        LARGE('L')
    }

    enum class PizzaIngredient(@DrawableRes val image: Int) {
        BASIL(R.drawable.basil_group),
        BROCCOLI(R.drawable.broccoli_group),
        ONION(R.drawable.onion_group),
        MUSHROOM(R.drawable.mushroom_group),
        SAUSAGE(R.drawable.sausage_group),
    }
}
