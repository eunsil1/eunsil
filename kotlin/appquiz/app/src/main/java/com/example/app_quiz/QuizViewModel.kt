package com.example.app_quiz

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//화면 상태 저장 클래스
data class QuizUiState(
    val currentIndex: Int = 0, //현재 문제번호 - 0 - 1번 문제, 1 - 2번 문제, 2 - 3번 문제
    val score: Int = 0, //점수
    val isFinished: Boolean = false, //퀴즈 종료 여부
    val lastAnswerCorrect: Boolean? = null, //사용자의 최근 정답 결과
    // null -> 아직 선택 안함 true -> 정답 false -> 오답
){
    val currentQuestion: QuizQuestion? //currentIndex에 해당하는 현재 문제 QuizData.kt 목록을 가져옴
        get() = kotlinQuizQuestions.getOrNull(currentIndex)

    val totalQuestions: Int = kotlinQuizQuestions.size //문제의 갯수 (지금은 5개)
}

class QuizViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(QuizUiState()) //QuizUiState() - 현재 상태 저장, 외부수정 불가(private)
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow(); //ui 읽기 전용상태 공개(수정 불가)
    //MutableStateFlow = 상태(state)를 저장, 변경되면 UI 자동으로 알려줌 - 화면갱신
    //초기값 - 문제번호=0 점수=0 퀴즈종료=false 정답여부=null

    fun submitAnswer(choiceIndex: Int){ //사용자가 답을 눌렀을때 실행되는 함수
        val state = _uiState.value //현재문제 : 2 점수 : 2 - 현재 저장된 상태 가져오기
        if(state.isFinished || state.lastAnswerCorrect != null) return
        //퀴즈 끝 또는 이미 답 클릭 했는지(null(선택전), true(정답), false(오답)) - 더이상 선택금지(중복선택 금지)
        val question = state.currentQuestion ?: return //현재의 문제를 가져옴 - ?:return - null이면 함수종료
        val correct = choiceIndex == question.correctIndex //선택한 답과 정답 비교 - 같으면 true
        _uiState.update{ //현재 상태를 변경 -> ui 자동갱신
            it.copy( //기존상태 복사 - 일부만 변경
                lastAnswerCorrect = correct, //정답 / 오답 -> ui 표시용
                score = if(correct) it.score + 1 else it.score, //정답이면 +1 / 아니면 그대로
                //기존 점수 3점을 4점으로 변경
            ) //정답입니다. ! 점수 증가 - 버튼 비활성화
        }
    }

    fun nextQuestion(){
        _uiState.update{ state ->
            if(state.currentIndex < kotlinQuizQuestions.lastIndex){
                state.copy( //다음문제로 이동
                    currentIndex = state.currentIndex + 1, //현재 문제 번호 증가
                    lastAnswerCorrect = null, //lastAnswerCorrect = true, false -> null 초기화
                )
            }else{
                //현재 인덱스 번호가 마지막 인덱스 번호와 같으면
                state.copy(isFinished = true, lastAnswerCorrect = null)
            }
        }
    }

    fun restart(){ //새로 시작하면 QuizUiState()를 초기 상태 객체 생성
        _uiState.value = QuizUiState()
    }
}