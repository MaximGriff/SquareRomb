package com.example.squareromb

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    var selectedMethod by remember{mutableStateOf(1)}

    var currentEdge by remember { mutableFloatStateOf(1f) }
    var currentHeight by remember { mutableFloatStateOf(1f) }

    var currentFirstDiagonal by remember { mutableFloatStateOf(1f) }
    var currentSecondDiagonal by remember { mutableFloatStateOf(1f) }


    Column(){
        Text(
            text = "Выберите метод расчета площади",
            modifier = modifier
        )
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (selectedMethod == 1),
                onClick={selectedMethod = 1}
            )
            Text(text = "По стороне и высоте")
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (selectedMethod == 2),
                        onClick={selectedMethod = 2}
            )
            Text(text = "По диагоналям")
        }
        if (selectedMethod == 1){
            FormInputEdgeHeight(onChangeEdgeHeight = {edge, height ->
                currentEdge = edge
                currentHeight = height})
        }
        else {
            FormInputDiagonals(onChangeDiagonals = {firstDiagonal, secondDiagonal ->
                currentFirstDiagonal = firstDiagonal
                currentSecondDiagonal = secondDiagonal
            })
        }

    }
}

@Composable
fun FormInputEdgeHeight(onChangeEdgeHeight: (edge: Float,
                                             height: Float) -> Unit){
    var localEdge = remember { mutableFloatStateOf(1f) };
    var localHeight= remember { mutableFloatStateOf(1f) };

    Column()
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Text(" Высота: ")

            TextField(value = localHeight.value.toString(),
                onValueChange = {
                    localHeight.value = it.toFloat()
                    onChangeEdgeHeight(localEdge.value.toFloat(), it.toFloat())
                })
        }
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Text("Сторона: ")

            TextField(value = localEdge.value.toString(),
                onValueChange = {
                    localEdge.value = it.toFloat()
                    onChangeEdgeHeight(it.toFloat(), localHeight.value.toFloat())
                })
        }
    }
}

@Composable
fun FormInputDiagonals(onChangeDiagonals: (firstDiagonal: Float,
                                             secondDiagonal: Float) -> Unit){
    var localFirstDiagonal = remember { mutableFloatStateOf(1f) };
    var localSecondDiagonal= remember { mutableFloatStateOf(1f) };

    Column()
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Text(" Первая диагональ: ")

            TextField(
                value = localFirstDiagonal.value.toString(),
                onValueChange = { newText ->
                    if (newText.isEmpty() || newText.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        val newValue = newText.toFloatOrNull() ?: 0f
                        localFirstDiagonal.value = newValue
                        onChangeDiagonals(newValue, localSecondDiagonal.value)
                    }
                },
                modifier = Modifier
                    .width(110.dp)
                    .height(48.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Text("Вторая диагональ: ")

            TextField(value = localSecondDiagonal.value.toString(),
                onValueChange = { newText ->
                    if (newText.isEmpty() || newText.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        val newValue = newText.toFloatOrNull() ?: 0f
                        localSecondDiagonal.value = newValue
                        onChangeDiagonals(newValue, localSecondDiagonal.value)
                    }
                },
                modifier = Modifier
                    .width(110.dp)
                    .height(48.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
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