package com.mooncake.pizaaapp.presentation.main.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPath() {
    var fullGraphPath by remember { mutableStateOf(Path()) }
    val pathMeasure = remember { PathMeasure() }
    val drawPathAnimation = remember { Animatable(0f) }
    val animatedPath = remember {
        derivedStateOf {
            val newPath = Path()
            pathMeasure.setPath(fullGraphPath, false)
            pathMeasure.getSegment(
                0f,
                drawPathAnimation.value * pathMeasure.length,
                newPath,
                true
            )
            newPath
        }
    }

    Canvas(modifier = Modifier.size(400.dp)) {
        fullGraphPath = getHeartPath()
        drawPath(
            path = animatedPath.value,
            color = Color.Black,
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }

    LaunchedEffect(key1 = Unit) {
        drawPathAnimation.animateTo(
            1f,
            animationSpec = tween(10000, easing = LinearEasing)
        )
    }
}

private fun DrawScope.getHeartPath(): Path {
    return Path().apply {
        moveTo(size.width * 0.5f, size.height * 0.9f)
        cubicTo(
            size.width * -0.2f,
            size.height * 0.3f,
            size.width * 0.3f,
            size.height * 0.1f,
            size.width * 0.5f,
            size.height * 0.4f,
        )
        cubicTo(
            size.width * 0.5f,
            size.height * 0.1f,
            size.width * 1.2f,
            size.height * 0.3f,
            size.width * 0.5f,
            size.height * 0.9f,
        )
        close()
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAnimatedPath() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.border(width = 10.dp, color = Color.Green)){
            AnimatedPath()
        }
    }
}

