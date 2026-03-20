import React from 'react'
import Counter from './Counter'
import InputSample from './InputSample';
import UserList from './UserList';
import { useRef } from 'react';
import CreateUser from './CreateUser';
import { useState } from 'react';

function App() {
  
  //입력값을 하나의 객체로 관리
  const [inputs,setInputs] = useState({
    username:'',
    email:''
  });

  const {username, email} = inputs; //구조분해로 쉽게 사용
  const onChange = (e) => { //하나의 함수로 name = username, name = email 입력 이벤트
    const {name, value} = e.target;
    setInputs({
      ...inputs,
      [name] : value
    });
  }
  //input 입력 -> onChange 실행 -> inputs 상태 변경 -> 화면에 반영

  //사용자 목록 저장 배열
  const [users,setUsers] = useState([
    {
      id: 1,
      username: 'velopert',
      email: 'public.velopert@gmail.com'
    },
    {
      id: 2,
      username: 'tester',
      email: 'tester@example.com'
    },
    {
      id: 3,
      username: 'liz',
      email: 'liz@example.com'
    }
  ]);

  const nextId = useRef(4); //useRef() 사용할 때 파라미터 넣어주면 .current 기본값
  const onCreate = () => {
    //나중에 구현 할 배열에 항목 추가하는 로직
    const user = {
      id : nextId.current,
      username,
      email
    }//const user = {id,username,email}

    setUsers([ 
      ...users, //...users 기존 배열 + 새 데이터(user) -> 불변성 유지
      user
    ]);

    setInputs({
      username:'',
      email:''
    }); //입력 초기화
    nextId.current += 1; //다음 사용자 id 준비
  }

  const onRemove = (id) => {
    //id가 일치하는 사용자를 제외하고 새로운 배열을 만들어 state에 넣는다
    setUsers(users.filter(user => user.id !== id));
    //filter는 조건에 만족하는 것만 남김
  }

   const onToggle = id => {
    setUsers(
      users.map(user => //배열을 하나씩 돌면서 새로운 배열 생성
        user.id === id ? { ...user, active: !user.active } : user
      )
    );
  };
  //user.id === id 클릭한 사용자
  //id 같으면 -> active 값 뒤집기

  //onToggle
  //map 실행 -> 해당 user만 active 변경
  //=> setUsers로 상태 업데이트
  //-> 다시 화면 핸더링


  return (
    <div>
      <CreateUser 
        username={username}
        email={email}
        onCreate={onCreate}
        onChange={onChange}
        // 값은 : username, email 이벤트 : onChange, onCreate
      />
      <UserList users={users} onRemove={onRemove} onToggle={onToggle}/>
      {/* users 배열 받아서 리스트 출력 */}
    </div>
  )
}

export default App;

// 1. input 입력
// 2. onChange → inputs 상태 변경
// 3. 버튼 클릭
// 4. onCreate 실행
// 5. users 배열에 추가
// 6. UserList 재렌더링
// 7. 화면에 사용자 추가됨

//onRemove(id) 실행
//filter로 해당 id 제거
//setUsers로 상태 변경
//화면에 다시 랜더링