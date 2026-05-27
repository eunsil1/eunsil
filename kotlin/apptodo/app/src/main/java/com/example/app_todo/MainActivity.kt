package com.example.app_todo

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

data class TodoItem(
    val id: Long,
    val title: String,
    val done: Boolean = false,
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                TodoScreen()
            }
        }
    }
}

@Composable
fun TodoScreen() {
    val todos = remember { mutableStateListOf<TodoItem>() }
    var input by remember { mutableStateOf("") }
    var nextId by remember { mutableStateOf(1L) }

    fun addTodo() {
        val title = input.trim()
        if (title.isEmpty()) return
        todos.add(TodoItem(id = nextId++, title = title))
        input = ""
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text("할 일 목록", style = MaterialTheme.typography.headlineMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text("할 일 입력") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                )
                TextButton(onClick = { addTodo() }) {
                    Text("추가")
                }
            }

            if (todos.isEmpty()) {
                Text(
                    "할 일을 추가해 보세요.",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(todos, key = { it.id }) { item ->
                        TodoRow(
                            item = item,
                            onToggle = { checked ->
                                val index = todos.indexOfFirst { it.id == item.id } //같은 id 목룍을 찾아서 
                                if (index >= 0) {
                                    todos[index] = item.copy(done = checked) //그항목을 done = checked 바꾼체 새 객체로 교체
                                }
                            },
                            //삭제 버튼 클리시 todos 에서 id가 같은 항목을 전부 제거
                            onDelete = { todos.removeAll { it.id == item.id } },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodoRow(
    item: TodoItem,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = item.done, onCheckedChange = onToggle)
        Text(
            text = item.title,
            modifier = Modifier.weight(1f),
            textDecoration = if (item.done) TextDecoration.LineThrough else null,
        )
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "삭제")
        }
    }
}



