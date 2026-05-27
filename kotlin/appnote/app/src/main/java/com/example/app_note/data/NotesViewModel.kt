package com.example.app_note.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_note.data.AppDatabase
import com.example.app_note.data.NoteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    //ViewModel 만들어 질때 한번에 DB, DAO 연결
    private val dao = AppDatabase.getInstance(application).noteDao()

    //Flow<List<NoteEntity>> 메모리 실시간 검사
    val notes = dao.observeAll() //DB메모 목록 가져오기
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    //viewModelScope - viewModel 살아 있을때만 구독, UI 구독자 0명 -> 5초후에 upstream 중단 / emptyList() - 첫 로딩 전 기본값(빈목록)

    fun addNote(title: String, body: String) { //메모 저장 함수
        val trimmedTitle = title.trim()
        if (trimmedTitle.isEmpty()) return //빈 제목 방지
        viewModelScope.launch {
            dao.insert(NoteEntity(title = trimmedTitle, body = body.trim())) //db 저장
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            dao.delete(note)
        }
    }
}
