package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPath(

) {
    val drawPathAnimation = remember {
        Animatable(0f)
    }
    val pathMeasure = remember {
        PathMeasure()
    }
    val fullGraphPath = remember {
        mutableStateOf(Path().apply {
            moveTo(0f,0f)
            lineTo(500f,0f)
            lineTo(500f,500f)
            lineTo(0f,500f)
        })
    }
    LaunchedEffect(key1 = Unit, block = {
        drawPathAnimation.animateTo(
            1f,
            animationSpec = tween(10000)
        )
    })
    val animatedPath = remember {
        derivedStateOf {
            val newPath = Path()
            pathMeasure.setPath(fullGraphPath.value, false)
            pathMeasure.getSegment(
                0f,
                drawPathAnimation.value * pathMeasure.length,
                newPath,
                false
            )
            newPath
        }
    }
    Canvas(modifier = Modifier.size(200.dp)) {
        drawPath(path = animatedPath.value, color = Color.Cyan, style = Stroke(width = 4.dp.toPx()))
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAnimatedPath() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedPath()
    }
}

