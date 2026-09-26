package com.example.squareromb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.squareromb.ui.theme.SquareRombTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SquareRombTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeneralFormCalcSquareRomb(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun CalculateSquareByEdgeHeight(edge: Double, height: Double) = edge * height

fun CalculateSquareDiagonal(firstDiagonal: Double,
                            secondDiagonal: Double) =
    (firstDiagonal * secondDiagonal) / 2

@Composable
fun GeneralFormCalcSquareRomb(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SquareRombTheme {
        GeneralFormCalcSquareRomb("Android")
    }
}