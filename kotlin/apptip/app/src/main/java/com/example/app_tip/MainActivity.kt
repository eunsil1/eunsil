package com.example.app_tip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_tip.ui.theme.ApptipTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TipCalculatorScreen()
            }
        }
    }
}

@Composable
fun  TipCalculatorScreen(){
    var billText by remember { mutableStateOf("") }
    var tipPercent by remember { mutableFloatStateOf(15f) } //float

    val billAmount = billText.toDoubleOrNull() ?: 0.0 // "1000" ->  1000  입력없으면 0
    val tipAmount = billAmount * tipPercent / 100
    val total = billAmount + tipAmount
    //(통화)원표시
    val formatter = remember { NumberFormat.getCurrencyInstance(Locale.KOREA) }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ){
            Text("팁 계산기", style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField( //입력창
                value = billText, //현재입력값
                onValueChange = { billText = it.filter { c -> c.isDigit() || c == '.' } }, //숫자와 '.'만 입력허용
                label = { Text("결제 금액 (원)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //휴대폰숫자 키패드
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            Text("팁 비율: ${tipPercent.roundToInt()}%")
            Slider( //슬라이더 바
                value = tipPercent,
                onValueChange = { tipPercent = it },
                valueRange = 0f..30f, //0~30%
                steps = 29,
            )
            Card(modifier = Modifier.fillMaxWidth()) { //결과내 박스
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("팁: ${formatter.format(tipAmount)}")
                    Text("합계: ${formatter.format(total)}", style = MaterialTheme.typography.titleLarge)
                }
            }
        }


    }

}

