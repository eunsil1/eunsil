import React from 'react'

function CreateUser({username,email,onChange,onCreate}) {
  return (
    <div>
      <input 
        name='username'
        placeholder='계정명'
        onChange={onChange}
        value={username}
      />

      <input 
        name='email'
        placeholder='이메일'
        onChange={onChange}
        value={email}
      />
      <button onClick={onCreate}>등록</button>
      {/* 상태 관리를 CreateUser에서 하지 않고 부모 컴포넌트 App에서 하게 하고
      input의 값 및 이벤트로 등록할 함수들을 props로 넘겨받아 사용 */}
    </div>
  )
}

export default CreateUser

//입력폼(UI)만 담당하는 자식 컴포넌트
//1. 상태(state)
//2. 데이터는 부모에서 받음
//3. 이벤트도 부모에서 받음
// 입력창만 그려주는 역할
//{username,email,onChange,onCreate}
//username - 계정값, email - 이메일, onChange - input 변경 이벤트, onCreate - 등록 버튼 클릭 이벤트 

//input -> onChange -> App(state 변경) -> props -> input
//상태 끌어올리기(Lifting State Up)
//state 부모(App) - 자식은(CreateUser)은 UI만 담당