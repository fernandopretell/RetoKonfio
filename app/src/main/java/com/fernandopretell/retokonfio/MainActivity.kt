package com.fernandopretell.retokonfio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fernandopretell.retokonfio.presentation.doglist.DogListScreen
import com.fernandopretell.retokonfio.presentation.theme.DogsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DogListScreen()
                }
            }
        }
    }
}
