package com.mooncake.pizaaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mooncake.pizaaapp.presentation.main.MainScreen
import com.mooncake.pizaaapp.presentation.main.MainViewModel
import com.mooncake.pizaaapp.presentation.ui.theme.PizaaAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizaaAppTheme {
                MainScreen(viewModel)
            }
        }
    }
}
