package com.example.app_timer

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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_timer.ui.theme.ApptimerTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                StopwatchScreen();
            }
        }
    }
}

@Composable
fun StopwatchScreen() {
    //seconds 초를 저장 초기값은 0  - remember 다시 화면이 그려져도 기억(데이터유지)
    // 타이머가 작동중(true) 멈춤 (false)
    var seconds by remember { mutableIntStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }

    //비동기 타이머 로직 isRunning 값이 바뀔때마다 감지 다시 실행 / 취소
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000) //1초대기
            seconds++ //1초증가 -> 상태가 바뀌었으므로 화면이 자동으로 새로 그려짐
        }
    }

    //UI 레이아웃 구성
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier //UI의 모양 , 크기 ,위치를 변경 도구
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            //타이틀
            Text("스톱워치", style = MaterialTheme.typography.headlineMedium)
            //시간표시만 (00:00 포맷)
            Text(
                text = formatTime(seconds),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 32.dp),
            )
            //제어버튼
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                //시작 / 일시정지 토글버튼
                Button(onClick = { isRunning = !isRunning }) {
                    Text(if (isRunning) "일시정지" else "시작")
                }
                //리셋버튼(타이머를 멈추고 0초로 초기화)
                OutlinedButton(onClick = {
                    isRunning = false
                    seconds = 0
                }) {
                    Text("리셋")
                }
            }
        }

    }


}
private fun formatTime(totalSeconds: Int) : String{
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}

// (totalSeconds 5초 5/60 = 0분 , 5% 60 = 5초   00:05
// (totalSeconds 65초 65/60 = 1분 , 65% 60 = 5초   01:05
