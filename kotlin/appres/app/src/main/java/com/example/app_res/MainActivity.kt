package com.example.app_res

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                ResDemoScreen()
            }
        }
    }
}

@Composable
fun ResDemoScreen() {
    val tips = stringArrayResource(R.array.tips)
    var tipIndex by remember { mutableIntStateOf(0) } //초기값을 0 번째팁 을 보여주세요

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = stringResource(R.string.screen_title),
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(R.color.primary_text),
            )
            Text(
                text = stringResource(R.string.subtitle),
                color = colorResource(R.color.accent_orange),
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.card_background),
                ),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = stringResource(R.string.label_drawable),
                        modifier = Modifier.size(40.dp),
                        colorFilter = ColorFilter.tint(colorResource(R.color.star_tint)),
                    )
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(R.string.label_drawable),
                            fontWeight = FontWeight.Bold,
                        )
                        Text(stringResource(R.string.label_strings))
                        Text(stringResource(R.string.label_colors))
                    }
                }
            }

            Text(
                text = tips[tipIndex],
                modifier = Modifier.fillMaxWidth(),
                color = colorResource(R.color.primary_text),
            )

            Button(
                onClick = { tipIndex = (tipIndex + 1) % tips.size }, //끝까지 가면 다시 처음으로
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.button_next))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "앱 이름도 res: @string/app_name → ${stringResource(R.string.app_name)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
