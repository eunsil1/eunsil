import React from 'react'
import {createSlice} from '@reduxjs/toolkit'

//counter의 초기값 0 -> 전역 상태 초기값
const initialState = {
  value : 0,
}

//createSlice - 리덕스 핵심 3개
//name - 상태의 이름(구분), state - 데이터, reducers - 상태 변경 로직 
//createSlice = 상태 + 액션 + 리듀서를 한번에 만드는 도구
export const counterSlice = createSlice({
  name : "counter",
  initialState,
  reducers : {
    increment : (state) =>{
      state.value += 1;
    },
    decrement : (state) =>{
      state.value -= 1;
    },
    reset : (state) => {
      state.value = 0;
    },
  },

})



export const{increment, decrement, reset} = counterSlice.actions;
//dispatch(increment) - 버튼 클릭 시 사용할 명령어
export default counterSlice.reducer;
//store에 등록할 상태 관리함수
//버튼 클릭 -> dispatch(increment) -> reducers 실행 -> state.value 1증가 -> 화면 업데이트

//jsx 문법으로 html 변환하는데 jsx 없음 저장 시 에러나면 .js 확장자로 변환