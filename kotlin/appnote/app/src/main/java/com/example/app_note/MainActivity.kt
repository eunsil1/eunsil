package com.example.app_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_note.data.NoteEntity
import com.example.app_note.data.NotesViewModel
import com.example.app_note.ui.theme.AppnoteTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                NotesScreen()
            }
        }
    }
}

@Composable
fun NotesScreen(viewModel: NotesViewModel = viewModel()){
    val notes by viewModel.notes.collectAsState() //Composable이 다시 그려도 값 유지
    var title by remember {mutableStateOf("") } //제목 입력창 상태
    var body by remember {mutableStateOf("")}

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text("메모장", style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField( //제목
                value = title,
                onValueChange = { title = it }, //타이핑할때 title 갱신 -> 화면 재그리기
                label = { Text("제목") }, //"제목"라벨
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                singleLine = true, //한줄만(엔터로 줄바꿈 안됨)
            )
            OutlinedTextField( //내용
                value = body,
                onValueChange = { body = it },
                label = { Text("내용") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                minLines = 2, //최소 2줄 높이, 여러줄 입력 가능
            )
            TextButton(
                onClick = {
                    viewModel.addNote(title, body) //db에 insert
                    title = "" // -> 입력창 비우기
                    body = ""
                },
                modifier = Modifier.align(Alignment.End), //컬럼인 오른쪽 정렬
            ) {
                Text("저장")
            }

            if (notes.isEmpty()) { //변경 후
                Text(
                    "저장된 메모가 없습니다.", //안내문구
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(notes, key = { it.id }) { note -> //notes 목록 리스트
                        NoteCard( //메모 하나당 카드 하나
                            note = note,
                            onDelete = { viewModel.deleteNote(note) }, //삭제(휴지통) 클릭하면
                            //-> ViewModel -> dao.delete(note) -> Flow -> 목록 자동 갱신
                        )
                    }
                }
            }
        }
    }
}

//메모 1개, 카드 1개 왼쪽 텍스트 오른쪽 삭제 아이콘
@Composable
fun NoteCard(note: NoteEntity, onDelete: () -> Unit) {
    val dateText = remember(note.createdAt) {
        SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA).format(Date(note.createdAt))
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(note.title, style = MaterialTheme.typography.titleMedium)
                if (note.body.isNotEmpty()) {
                    Text(
                        note.body,
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Text(
                    dateText,
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "삭제")
            }
        }
    }
}
