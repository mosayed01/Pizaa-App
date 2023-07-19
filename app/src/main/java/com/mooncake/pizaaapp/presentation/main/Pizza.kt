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

    enum class PizzaIngredient(
        @DrawableRes val imageGroup: Int,
        @DrawableRes val imageItem: Int,
    ) {
        BASIL(R.drawable.basil_group, R.drawable.basil),
        BROCCOLI(R.drawable.broccoli_group, R.drawable.broccoli),
        ONION(R.drawable.onion_group, R.drawable.onion),
        MUSHROOM(R.drawable.mushroom_group, R.drawable.mushroom),
        SAUSAGE(R.drawable.sausage_group, R.drawable.sausage),
    }

    val selectedPricePrice
        get() = prices[size]
}
