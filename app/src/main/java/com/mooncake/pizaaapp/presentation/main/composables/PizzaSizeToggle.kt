package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mooncake.pizaaapp.presentation.main.Pizza
import com.mooncake.pizaaapp.presentation.ui.theme.WhiteFD

@Composable
fun PizzaSizeToggle(
    onSelectNewSize: (Pizza.PizzaSize) -> Unit,
    pizzaSize: Pizza.PizzaSize,
    modifier: Modifier = Modifier
) {
    val circleSize = 30.dp
    val translateXFactor by animateFloatAsState(
        targetValue = when (pizzaSize) {
            Pizza.PizzaSize.SMALL -> -1f
            Pizza.PizzaSize.MEDIUM -> 0f
            Pizza.PizzaSize.LARGE -> 1f
        },
        animationSpec = spring(Spring.DampingRatioLowBouncy, Spring.StiffnessLow)
    )
    val paddingHorizontal = 24.dp
    val paddingHorizontalFloat = LocalDensity.current.run { paddingHorizontal.toPx() }

    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationX = translateXFactor * (size.width + paddingHorizontalFloat / 5)
                }
                .size(circleSize)
                .clip(CircleShape)
                .shadow(
                    10.dp,
                    shape = CircleShape,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(WhiteFD)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(paddingHorizontal)
        ) {
            Text(
                text = Pizza.PizzaSize.SMALL.char.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.clip(CircleShape).clickable { onSelectNewSize(Pizza.PizzaSize.SMALL) },
                color = Color.Black
            )
            Text(
                text = Pizza.PizzaSize.MEDIUM.char.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.clip(CircleShape).clickable { onSelectNewSize(Pizza.PizzaSize.MEDIUM) },
                color = Color.Black
            )
            Text(
                text = Pizza.PizzaSize.LARGE.char.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onSelectNewSize(Pizza.PizzaSize.LARGE) },
                color = Color.Black
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF364736)
@Composable
private fun Preview() {
    var size by remember { mutableStateOf(Pizza.PizzaSize.SMALL) }
    Column {
        PizzaSizeToggle(onSelectNewSize = { size = it }, pizzaSize = size)
    }
}