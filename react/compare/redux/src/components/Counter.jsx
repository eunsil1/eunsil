import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { decrement, increment, reset } from "../store/counterSlice";

export default function Counter() {
  const count = useSelector((state) => state.counter.value); //count 읽어오기
  const dispatch = useDispatch(); //state 변경 -> redux에서 액션 실행 요청하는 함수
  //redux의 상태를 변경해 달라는 함수
  return (
    <div className="counter">
      <div className="counter-display">{count}</div>
      <div className="button-group">
        <button onClick={() => dispatch(decrement())}>-</button>
        <button onClick={() => dispatch(reset())}>Reset</button>
        <button onClick={() => dispatch(increment())}>+</button>
      </div>
    </div>
  );
}

//- 클릭
//dispatch(decrement()) -> reducer 실행 -> state.value - 1 -> 화면 업데이트
