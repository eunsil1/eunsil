import React from 'react'
import { useState } from 'react'
//여러개의 인풋처리(객체 활용)
function MultiInput() {
  const [inputs, setInputs] = useState({
    username:'',
    nickname:'',
    email:''
  });

  //비구조화 할당을 통해 값 추출
  const {username,nickname,email} = inputs;

  const onChange = (e) => {
    const {value, name} = e.target; //우선 e.target에서 name, value를 추출
    setInputs({
      ...inputs, //기존의 input 객체를 복사한 뒤(Spread 연산자)
      [name] : value //name 키를 가진 값을 value로 설정
    });
  }

  const onReset = () => {
    setInputs({
      username:'',
      nickname:'',
      email:''
    });
  }

  return (
    <div>
      <input 
      name='username'
      placeholder='이름'
      onChange={onChange} 
      value={username}
      />

      <input 
      name='nickname'
      placeholder='닉네임'
      onChange={onChange} 
      value={nickname}
      />

      <input 
      name='email'
      placeholder='이메일'
      onChange={onChange} 
      value={email}
      />

      <button onClick={onReset}>초기화</button>
      <div>
        <b>값 : {username}({nickname} - {email})</b>
      </div>
    </div>
  )
}

export default MultiInput