package com.mooncake.pizaaapp.data

import com.mooncake.pizaaapp.R
import com.mooncake.pizaaapp.presentation.main.Pizza

object DataSource {
    fun getAllPizza(): List<Pizza>{
        return pizzaList
    }

    private val pizzaList = listOf(
        Pizza(
            id = 0,
            image = R.drawable.bread_1,
            size = Pizza.PizzaSize.MEDIUM,
            prices = mapOf(Pizza.PizzaSize.SMALL to 12, Pizza.PizzaSize.MEDIUM to 18, Pizza.PizzaSize.LARGE to 25),
        ),
        Pizza(
            id = 1,
            image = R.drawable.bread_2,
            size = Pizza.PizzaSize.MEDIUM,
            prices = mapOf(Pizza.PizzaSize.SMALL to 15, Pizza.PizzaSize.MEDIUM to 22, Pizza.PizzaSize.LARGE to 30),
        ),
        Pizza(
            id = 2,
            image = R.drawable.bread_3,
            size = Pizza.PizzaSize.MEDIUM,
            prices = mapOf(Pizza.PizzaSize.SMALL to 15, Pizza.PizzaSize.MEDIUM to 25, Pizza.PizzaSize.LARGE to 35),
        ),
        Pizza(
            id = 3,
            image = R.drawable.bread_4,
            size = Pizza.PizzaSize.MEDIUM,
            prices = mapOf(Pizza.PizzaSize.SMALL to 15, Pizza.PizzaSize.MEDIUM to 30, Pizza.PizzaSize.LARGE to 45),
        ),
        Pizza(
            id = 4,
            image = R.drawable.bread_5,
            size = Pizza.PizzaSize.MEDIUM,
            prices = mapOf(Pizza.PizzaSize.SMALL to 10, Pizza.PizzaSize.MEDIUM to 15, Pizza.PizzaSize.LARGE to 20),
        ),
    )
}