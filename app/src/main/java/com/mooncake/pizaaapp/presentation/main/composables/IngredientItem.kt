package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mooncake.pizaaapp.presentation.main.Pizza
import com.mooncake.pizaaapp.presentation.ui.theme.SimiGreen

@Composable
fun IngredientItem(
    onClickIngredient: (Pizza.PizzaIngredient) -> Unit,
    selected: Boolean,
    ingredient: Pizza.PizzaIngredient,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.85f else 1f,
        animationSpec = spring(Spring.DampingRatioHighBouncy)
    )
    val tint by animateColorAsState(targetValue = if (selected) SimiGreen else Color.Transparent)

    Image(
        painter = painterResource(id = ingredient.imageItem),
        contentDescription = "ingredient ${ingredient.name}",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .scale(scale)
            .background(tint)
            .clickable(interactionSource, null) { onClickIngredient(ingredient) }
    )
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFCFFFC)
@Composable
private fun PreviewIngredientItem() {
    LazyRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(Pizza.PizzaIngredient.values()) {
            IngredientItem(onClickIngredient = {}, selected = true, it)
        }
    }
}