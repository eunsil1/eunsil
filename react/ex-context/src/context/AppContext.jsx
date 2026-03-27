import React from 'react'
import { createContext, useContext, useState, useMemo } from 'react'
//props 제거용 
//props 어디서든 상태 사용하기

const AppContext = createContext(null);
//전역 상태를 저장할 공간 생성 (나중에 Provider가 실제값을 넣어줌)

export function AppProvider({children}) {
  const [theme, setTheme] = useState('light');
  const [count, setCount] = useState(0);
  //전역으로 사용할 내용 
  //theme - 라이트 / 다크모드 
  //count - 숫자 카운트

  //상태변경함수 - 테마변경
  const toggleTheme = () => {
    setTheme((t) => (t === 'light' ? 'dark' : 'light'));
  };

  //상태변경함수 - 숫자를 변경
  const increment = () => setCount ((c) => c + 1);

  //context 값이 바뀌면 모든 자식 컴포넌트 리렌더링
  //useMemo - theme, count 변경
  const value = useMemo(() => ({
    theme,
    count,
    toggleTheme,
    increment
  }),[theme,count])

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>
  //AppProvider() 앱 전체를 감싸서 상태를 공유
}

export function useApp(){
  const ctx = useContext(AppContext) //Context 값을 가져오기
  if(!ctx){ //Provider 밖에서 쓰면 에러
    throw new Error('useApp은 AppProvider 안에서만 사용하세요.')
  }
  return ctx; //Context 값 리턴
}