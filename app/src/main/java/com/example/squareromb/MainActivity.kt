package com.example.squareromb

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
    var currentValueSquare: Double? = null;
    //var selectedMethod: Number = 0;

    var selectedMethod = remember{mutableStateOf(1)}

    Column(){
        Text(
            text = "Выберите метод расчета площади",
            modifier = modifier
        )
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (selectedMethod.value == 1),
                onClick={selectedMethod.value = 1}
            )
            Text(text = "По стороне и высоте")
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (selectedMethod.value == 2),
                        onClick={selectedMethod.value = 2}
            )
            Text(text = "По диагоналям")
        }
    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SquareRombTheme {
        GeneralFormCalcSquareRomb("Android")
    }
}