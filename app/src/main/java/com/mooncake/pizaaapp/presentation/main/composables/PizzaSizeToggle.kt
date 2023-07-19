package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mooncake.pizaaapp.presentation.main.Pizza
import com.mooncake.pizaaapp.presentation.ui.theme.WhiteFD

@Composable
fun PizzaSizeToggle(
    onSelectNewSize: (Pizza.PizzaSize) -> Unit,
    pizzaSize: Pizza.PizzaSize,
    modifier: Modifier = Modifier,
    circleSize: Dp = 40.dp,
    circleColor: Color = WhiteFD,
    paddingHorizontal: Dp = 16.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(
        textAlign = TextAlign.Center,
        color = Color.Black
    )
) {
    val interactionSource = MutableInteractionSource()
    val translateXFactor by animateFloatAsState(
        targetValue = when (pizzaSize) {
            Pizza.PizzaSize.SMALL -> -1f
            Pizza.PizzaSize.MEDIUM -> 0f
            Pizza.PizzaSize.LARGE -> 1f
        },
        animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
    )
    val paddingHorizontalFloat = LocalDensity.current.run { paddingHorizontal.toPx() }

    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    shadowElevation = 20f
                    ambientShadowColor = Color.Black
                    spotShadowColor = Color.Black
                    translationX = translateXFactor * (size.width + paddingHorizontalFloat)
                }
                .size(circleSize)
                .background(circleColor)
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(paddingHorizontal)
        ) {
            Text(
                text = Pizza.PizzaSize.SMALL.char.toString(),
                style = textStyle,
                modifier = Modifier
                    .padding(top = circleSize * 0.2f)
                    .size(circleSize)
                    .clip(CircleShape)
                    .clickable(interactionSource = interactionSource,null) { onSelectNewSize(Pizza.PizzaSize.SMALL) },
            )
            Text(
                text = Pizza.PizzaSize.MEDIUM.char.toString(),
                style = textStyle,
                modifier = Modifier
                    .size(circleSize)
                    .clip(CircleShape)
                    .clickable(interactionSource = interactionSource,null) { onSelectNewSize(Pizza.PizzaSize.MEDIUM) },
            )
            Text(
                text = Pizza.PizzaSize.LARGE.char.toString(),
                style = textStyle,
                modifier = Modifier
                    .size(circleSize)
                    .clip(CircleShape)
                    .clickable(interactionSource = interactionSource,null) { onSelectNewSize(Pizza.PizzaSize.LARGE) },
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun Preview() {
    var size by remember { mutableStateOf(Pizza.PizzaSize.SMALL) }
    Column {
        PizzaSizeToggle(onSelectNewSize = { }, pizzaSize = Pizza.PizzaSize.SMALL)
        PizzaSizeToggle(onSelectNewSize = { }, pizzaSize = Pizza.PizzaSize.MEDIUM)
        PizzaSizeToggle(onSelectNewSize = { }, pizzaSize = Pizza.PizzaSize.LARGE)
        PizzaSizeToggle(onSelectNewSize = { size = it }, pizzaSize = size)
    }
}