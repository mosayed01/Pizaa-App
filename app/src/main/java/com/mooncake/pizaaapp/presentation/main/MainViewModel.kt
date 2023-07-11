package com.mooncake.pizaaapp.presentation.main

import androidx.lifecycle.ViewModel
import com.mooncake.pizaaapp.data.DataSource
import com.mooncake.pizaaapp.presentation.main.composables.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() {
        _state.update {
            it.copy(
                pizzaList = DataSource.getAllPizza(),
                ingredient = Pizza.PizzaIngredient.values().toList()
            )
        }
    }

    fun changeSize(pizzaId: Int, newSize: Pizza.PizzaSize) {
        state.value.pizzaList.find { it.id == pizzaId }?.let {
            updatePizzaItem(it.copy(size = newSize), pizzaId)
        }
    }

    fun toggleIngredient(pizzaId: Int, ingredient: Pizza.PizzaIngredient){
        if (state.value.pizzaList.isEmpty()){
            return
        }
        if (state.value.pizzaList[pizzaId].ingredients.contains(ingredient)){
            removeIngredient(pizzaId, ingredient)
        }else{
            addIngredient(pizzaId, ingredient)
        }
    }

    private fun removeIngredient(pizzaId: Int, ingredient: Pizza.PizzaIngredient) {
        state.value.pizzaList.find { it.id == pizzaId }?.let {
            updatePizzaItem(it.copy(ingredients = it.ingredients - ingredient), pizzaId)
        }
    }

    private fun addIngredient(pizzaId: Int, ingredient: Pizza.PizzaIngredient) {
        state.value.pizzaList.find { it.id == pizzaId }?.let {
            updatePizzaItem(it.copy(ingredients = it.ingredients + ingredient), pizzaId)
        }
    }

    private fun updatePizzaItem(newItem: Pizza, pizzaId: Int) {
        val oldList = state.value.pizzaList
        val newList = ((oldList - oldList[pizzaId]) + newItem).sortedBy { it.id }
        _state.update { it.copy(pizzaList = newList) }
    }
}