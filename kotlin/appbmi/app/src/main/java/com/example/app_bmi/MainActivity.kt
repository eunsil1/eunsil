package com.example.app_bmi

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_bmi.ui.theme.AppbmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme{
                BmiScreen()
            }
        }
    }
}

@Composable
fun BmiScreen(){
    var heightText by remember { mutableStateOf("") }
    var weightText by remember { mutableStateOf("")}
    var result by remember { mutableStateOf<BmiResult?>(null)}

    fun calculate(){
        val heightCm = heightText.toDoubleOrNull()
        val weightKg = weightText.toDoubleOrNull()
        if(heightCm == null || weightKg == null || heightCm <= 0.0){
            result = null
            return
        }
        val heightM = heightCm / 100.0
        val bmi = weightKg / (heightM * heightM)
        result = BmiResult(bmi = bmi, category = bmiCategory(bmi))
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            Text("BMI 계산기", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField( //Input 창
                value = heightText,
                onValueChange = {heightText = it.filter { c -> c.isDigit() || c == '.' }},
                label = { Text("키 (cm)")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //숫자 키패드
                singleLine = true, //한줄허용
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField( //Input 창
                value = weightText,
                onValueChange = {weightText = it.filter { c -> c.isDigit() || c == '.' }},
                label = { Text("몸무게 (kg)")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //숫자 키패드
                singleLine = true, //한줄허용
                modifier = Modifier.fillMaxWidth(),
            )

            Button(onClick = { calculate()}, modifier = Modifier.fillMaxWidth()) {
                Text("계산하기")
            }

            result?.let { bmiResult ->
                Card(modifier = Modifier.fillMaxWidth()){
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement
                    = Arrangement.spacedBy(8.dp) ) {
                        Text(
                            text = "BMI: %.1f".format(bmiResult.bmi),
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text("판정: ${bmiResult.category}")
                    }
                }
            }
        }
    }
}

//1. BMI 계산 결과를 묶어서 객체처럼 관리하는 데이터 바구니
data class BmiResult(val bmi: Double, val category: String)
//result =
//BmiResult(
//bmi = 22.5,
//category = "정상"
//)

//2. 대한민국 실정에 맞춘 BMI 수치별 판정 함수(when 표현식 활용)
fun bmiCategory(bmi: Double):String = when{
    bmi < 18.5 -> "저체중"
    bmi < 23.0 -> "정상"
    bmi < 25.0 -> "과체중"
    else -> "비만"
}
