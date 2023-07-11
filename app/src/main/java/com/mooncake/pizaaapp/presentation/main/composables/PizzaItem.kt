package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mooncake.pizaaapp.R
import com.mooncake.pizaaapp.presentation.main.Pizza

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PizzaItem(
    pizza: Pizza,
    modifier: Modifier = Modifier,
    spec: FiniteAnimationSpec<Float> = spring(
        dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessMedium
    )
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val scale by animateFloatAsState(
        targetValue = when (pizza.size) {
            Pizza.PizzaSize.SMALL -> 0.75f
            Pizza.PizzaSize.MEDIUM -> 0.85f
            Pizza.PizzaSize.LARGE -> 1f
        },
        animationSpec = spec
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = pizza.image),
            contentDescription = "pizza",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size((screenWidth * 0.52).dp)
                .scale(scale)
        )
        Pizza.PizzaIngredient.values().forEach { ingredient ->
            AnimatedVisibility(
                visible = pizza.ingredients.contains(ingredient),
                enter = scaleIn(initialScale = 4f, animationSpec = spec),
                exit = fadeOut(animationSpec = spec)
            ) {
                Image(
                    painter = painterResource(id = ingredient.image),
                    contentDescription = "ingredient",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size((screenWidth * 0.4).dp)
                        .scale(scale)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    var pizza by remember {
        mutableStateOf(
            Pizza(
                id = 0,
                R.drawable.bread_1,
                prices = mapOf(
                    Pizza.PizzaSize.SMALL to 10,
                    Pizza.PizzaSize.MEDIUM to 15,
                    Pizza.PizzaSize.LARGE to 30
                ),
                size = Pizza.PizzaSize.LARGE,
            )
        )
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PizzaItem(pizza = pizza)
        Button(onClick = { pizza = pizza.copy(size = Pizza.PizzaSize.values().random()) }) {
            Text(text = "scale")
        }
        Button(onClick = {
            pizza = pizza.copy(
                ingredients = pizza.ingredients + Pizza.PizzaIngredient.values().random()
            )
        }) {
            Text(text = "ingredients")
        }
    }
}