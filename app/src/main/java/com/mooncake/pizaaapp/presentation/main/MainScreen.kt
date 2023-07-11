package com.mooncake.pizaaapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooncake.pizaaapp.data.DataSource
import com.mooncake.pizaaapp.presentation.main.composables.IngredientItem
import com.mooncake.pizaaapp.presentation.main.composables.MainUiState
import com.mooncake.pizaaapp.presentation.main.composables.PizzaItem
import com.mooncake.pizaaapp.presentation.main.composables.PizzaSizeToggle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()
    MainScreenContent(
        viewModel::toggleIngredient,
        viewModel::changeSize,
        state,
        rememberPagerState()
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenContent(
    onClickIngredient: (Int, Pizza.PizzaIngredient) -> Unit,
    onSelectNewSize: (Int, Pizza.PizzaSize) -> Unit,
    state: MainUiState,
    pagerState: PagerState,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "back",
            )
            Text(
                text = "Pizza",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "back",
            )
        }

        HorizontalPager(
            pageCount = state.pizzaList.size,
            state = pagerState
        ) { PizzaItem(pizza = state.pizzaList[it]) }

        Text(
            text = "$${state.pizzaList[pagerState.currentPage].selectedPrice}",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        PizzaSizeToggle(
            onSelectNewSize = { newSize ->
                onSelectNewSize(
                    pagerState.currentPage,
                    newSize
                )
            },
            pizzaSize = state.pizzaList[pagerState.currentPage].size
        )

        Text(
            text = "CUSTOMIZE YOUR PIZZA",
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = Color.Black.copy(0.38f),
            modifier = Modifier.align(Alignment.Start).padding(16.dp)
        )

        LazyRow(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(Pizza.PizzaIngredient.values()) { ingredient ->
                IngredientItem(
                    onClickIngredient = { onClickIngredient(pagerState.currentPage, ingredient) },
                    selected = state.pizzaList[pagerState.currentPage].ingredients.contains(
                        ingredient
                    ),
                    ingredient
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewMainScreen() {
    MainScreenContent(
        onClickIngredient = { _, _ -> },
        onSelectNewSize = { _, _ -> },
        state = MainUiState(DataSource.getAllPizza().toMutableList()),
        rememberPagerState()
    )
}