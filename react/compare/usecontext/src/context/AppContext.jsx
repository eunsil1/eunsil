import React, { createContext, useContext, useState } from 'react'

//Context 생성 - 전역 데이터 통로(빈통로)
const AppContext = createContext();

//Provider(데이터 공급자)
//Context에 값을 넣어주는 컴포넌트
export default function AppProvider({children}) {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState({
    name: "홍길동",
    age: 25,
  });

  const updateUserName = (name) => setUser({ ...user, name });
  //기존의 user를 펼쳐 놓고 name 변경
  const updateUserAge = (age) => setUser({ ...user, age });

  const incrementCount = () => setCount(count + 1);
  const decrementCount = () => setCount(count - 1);
  const resetCount = () => setCount(0);

  //context로 값전달
  const value ={
    count,
    user,
    incrementCount,
    decrementCount,
    resetCount,
    updateUserName,
    updateUserAge,
  }//전역데이터

  return (
    <AppContext.Provider value={value}>
      {children}
    </AppContext.Provider>
    //Provider로 감싸진 모든 컴포넌트에서 -> value 접근 가능
  )
}


export function useAppContext() { //함수(훅)
  const context = useContext(AppContext); //쉽게 꺼내서 사용하기 좋게
  if(!context){
    throw new Error("useAppContext must be used within AppProvider")
  }
  return context;
}
