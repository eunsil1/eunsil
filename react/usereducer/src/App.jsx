import './App.css';
import React, { useReducer } from 'react'
import Header from './components/Header'
import List from './components/List'
import Editor from './components/Editor';
import { useRef } from 'react';

const mockData = [
  {
    id:0,
    isDone:false,
    content:"React 공부하기",
    date:new Date().getTime(),
  },

  {
    id: 1,
    isDone: false,
    content: "빨래하기",
    date: new Date().getTime(),
  },

  {
    id: 2,
    isDone: false,
    content: "노래 연습하기",
    date: new Date().getTime(),
  },
];
//상태변화 로직
function reducer(state, action){
  switch (action.type) {
    case "CREATE":
      return [action.newItem, ...state]
    case "UPDATE":
      return state.map((it)=>it.id === action.targetId ? {...it, isDone:!it.isDone} : it)
    case "DELETE":
      return state.filter((it)=>it.id !== action.targetId)
    default:
      return state;
  }
}
//state -> todo 배열
//action -> dispatch()에 보낼 명령 객체
//reducer -> 현재 상태 + 명령(action)을 받아서 새로운 상태(state) 반환
//dispatch가 실행되면 reducer가 호출 되어 
//type: "create"로 분기 -> return [action.newItem, ...state] 실행

//장점 - 코드구조 명확, 관리 / 확장 쉬움
//단점 - 코드가 useState보다 길어짐

function App() {
  // const [todos, setTodos] = useState(mockData); //mockData 초기값 입력
  const [todos, dispatch] = useReducer(reducer,mockData);
  //const [state, dispatch] = useReducer(reducer, initialstate);
  //state 현재의 상태값 (이전의 todo)
  //dispatch 상태변경명령(action)을 보낼 수
  //reducer => 상태를 실제로 변경하는 '로직함수'
  //initialstate =>초기 상태값
  //리액트에서 간단한 상태를 useState
  //상태가 많아지면 useReducer 사용한다.

  const idRef = useRef(3); //새로운 todo id를 만들기 위한 값

  const onCreate = (content) => {
    dispatch({
      type:"CREATE",
      newItem:{
        id : idRef.current,
        isDone : false,
        content : content,
        date : new Date().getTime(),
      }
      
    })
    idRef.current += 1
  };

  const onUpdate = (targetId) => {
    dispatch({
      type:"UPDATE",
      targetId
    })
  }

  const onDelete = (targetId) => {
    dispatch({
      type:"DELETE",
      targetId
    })
  }

  //전체 동작
  //사용자 클릭(입력) -> onCreate, onUpdate, onDelete로 분기 -> dispatch({type"","..."})
  //reducer(state, action) -> 새로운 state(todo) -> 컴포넌트 자동 랜더링

  //useReducer 복잡한 상태 관리용 리액트 훅
  //reducer() 상태 변경 로직을 한곳에 모은 함수
  //dispatch() 상태 변경 명령(action)을 보냄
 


  return (
    <div className='App'>
      <Header/>
      <Editor onCreate={onCreate}/>
      {/* App -> Editor 로 함수 전달 */}
      <List todos={todos} onUpdate={onUpdate} onDelete={onDelete}/>
      
    </div>
  )
}

export default App