package com.github.imanaijan.stockexchangeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.imanaijan.stockexchangeapp.ui.theme.StockExchangeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockExchangeAppTheme {

            }
        }
    }
}