package com.example.app_nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.app_nav.ui.theme.AppnavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme{
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp(){
    //rememberNavController() - 화면 이동을 명령/제어 - 운전기사
    val navController = rememberNavController()

    NavHost( //어떤 화면이 존재하고, 어디가 시작점인지 정의(지도/노선도)
        navController = navController,
        startDestination = "home", //앱의 첫 시작화면을 home으로 지정
    ){
        //[정거장 1] 홈화면
        composable(route = "home") {
            HomeScreen(
                onOpenDetail = {message ->
                    //상세화면으로 이동할때 문자열 데이터를 주소창(url)처럼 실시간으로 실어서 보냄
                    navController.navigate(route="detail/$message")
                },
            )
        }
        //[정거장 2] - 상세 화면 (데이터 받아야되므로 주소에 {message} 변수 저장)
        composable(
            "detail/{message}",
            arguments = listOf(navArgument("message"){type = NavType.StringType}),
        ) {
            backStackEntry ->
            //주소창(backStackEntry)에서 "message" key로 묶인 데이터를 꺼내옴
            val message = backStackEntry.arguments?.getString("message").orEmpty()
            DetailScreen(
                message = message,
                onBack = { navController.popBackStack()}, //뒤로 가기
            )
        }
    }

}

@Composable
fun DetailScreen(message: String, onBack: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("상세 화면", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = message,
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.titleLarge,
            )
            Button(
                onClick = onBack, //부모에게 이벤트 넘김
                modifier = Modifier.fillMaxWidth()

            ) {
                Text("뒤로가기")
            }
        }
    }
}

@Composable
fun HomeScreen(onOpenDetail: (String) -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("홈 화면", style = MaterialTheme.typography.headlineMedium)
            Text(
                "버튼을 누르면 두 번째 화면으로 이동합니다.",
                modifier = Modifier.padding(vertical = 16.dp),
            )
            Button(
                onClick = { onOpenDetail("Navigation 연습 성공!") }, //부모에게 이벤트 넘김
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("상세 화면으로")
            }
        }
    }
}





