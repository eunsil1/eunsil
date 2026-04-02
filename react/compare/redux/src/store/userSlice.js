import {createSlice} from '@reduxjs/toolkit'


const initialState = {
  name : "홍길동",
  age : 25, 
}

export const userSlice = createSlice({
  name : "user", //상태를 저장
  initialState,
  reducers : {
    updateName : (state, action) => {
      state.name = action.payload;
      //액션 자동 생성
      //현재 상태(state)의 name 값을 action.payload(사용자가 전달한 값)으로 변경해라
    },
    updateAge : (state, action) => {
      state.age = action.payload;
    },
  },
})
//dispatch(updateName("김미자")) 
//실제 내부적
//action = {
//  type : "user/updateName"
//  payload : "이순신"
//} 


export const{updateName, updateAge} = userSlice.actions;
export default userSlice.reducer;