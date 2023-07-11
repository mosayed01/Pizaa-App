package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val scale by animateFloatAsState(
        targetValue = when (pizza.size) {
            Pizza.PizzaSize.SMALL -> 0.75f
            Pizza.PizzaSize.MEDIUM -> 0.85f
            Pizza.PizzaSize.LARGE -> 1f
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = pizza.image),
            contentDescription = "pizza",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size((screenWidth * 0.52).dp).scale(scale)
        )
        pizza.ingredients.forEach { ingredient ->
            AnimatedContent(
                targetState = ingredient,
                transitionSpec = { scaleIn() with shrinkOut() }
            ) {
                Image(
                    painter = painterResource(id = it.image),
                    contentDescription = "ingredient",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size((screenWidth * 0.4).dp).scale(scale)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PizzaItem(
            pizza = Pizza(
                id = 0,
                R.drawable.bread_1,
                prices = mapOf(
                    Pizza.PizzaSize.SMALL to 10,
                    Pizza.PizzaSize.MEDIUM to 15,
                    Pizza.PizzaSize.LARGE to 30
                ),
                size = Pizza.PizzaSize.LARGE,
                ingredients = setOf(
                    Pizza.PizzaIngredient.BASIL,
                    Pizza.PizzaIngredient.MUSHROOM,
                )
            )
        )
        PizzaItem(
            pizza = Pizza(
                id = 0,
                R.drawable.bread_4,
                prices = mapOf(
                    Pizza.PizzaSize.SMALL to 10,
                    Pizza.PizzaSize.MEDIUM to 15,
                    Pizza.PizzaSize.LARGE to 30
                ),
                size = Pizza.PizzaSize.MEDIUM,
                ingredients = setOf(
                    Pizza.PizzaIngredient.BASIL,
                    Pizza.PizzaIngredient.BROCCOLI,
                    Pizza.PizzaIngredient.ONION,
                    Pizza.PizzaIngredient.SAUSAGE,
                )
            )
        )
        PizzaItem(
            pizza = Pizza(
                id = 0,
                R.drawable.bread_2,
                prices = mapOf(
                    Pizza.PizzaSize.SMALL to 10,
                    Pizza.PizzaSize.MEDIUM to 15,
                    Pizza.PizzaSize.LARGE to 30
                ),
                size = Pizza.PizzaSize.SMALL
            )
        )
    }
}