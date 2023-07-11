package com.mooncake.pizaaapp.presentation.main.composables

import com.mooncake.pizaaapp.presentation.main.Pizza

data class MainUiState(
    val pizzaList: List<Pizza> = emptyList(),
    val ingredient: List<Pizza.PizzaIngredient> = emptyList()
)
