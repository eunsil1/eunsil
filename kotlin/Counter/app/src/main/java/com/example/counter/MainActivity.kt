package com.example.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {//구글이 만든 디자인 규칙 - Material3
                CounterScreen()
            }
        }
    }
}

@Composable
fun CounterScreen() {
    var count by remember { mutableIntStateOf(0) } // mutableIntStateOf 정수가 변하면 갱신
//remember -> 화면이 다시 로드되도 값 유지
    Scaffold { innerPadding ->  //Scaffold  자동으로 만들어 주는 여백
        Column( //세로배치 - 현재 카운트 글자, 수자 , 증가 감소 버튼 
            modifier = Modifier
                .fillMaxSize() //화면 꽉채우기
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, //가로 가운데 정렬
            verticalArrangement = Arrangement.Center, //세로 가운데 정렬
        ) {
            Text(
                text = "현재 카운트",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = count.toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 32.dp),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { if (count > 0) count-- }) {
                    Text("-1")
                }
                Button(onClick = { count++ }) {
                    Text("+1")
                }
                Button(onClick = { count = 0 }) {
                    Text("리셋")
                }
            }
        }
    }
}
