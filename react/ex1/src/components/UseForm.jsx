import React from 'react'
import { useState } from 'react'

function UseForm() {
  const [name,setName] = useState('');
  const [age, setAge] = useState('');
  const [result, setResult] = useState(null);

  const handleClick = () => {
    if(!name || !age) {
      setResult({type : 'error', msg: '값을 입력하세요'})
    }else{
      setResult({ name, age })
    }
  }
  return (
    <div className='block'>
      <h2>1번 — 상태 관리 + 입력폼</h2>


      <div className='field'>
      <label>이름</label>
      <input
        value={name}
        onChange={e => setName(e.target.value)}
        placeholder='이름'
      />
      </div>

      <div className='field'>
      <label>나이</label>
      <input
        value={age}
        onChange={e => setAge(e.target.value)}
        placeholder='나이'
      />
      </div>

      <button className="confirm" onClick={handleClick}>확인</button>

      {result && result.type === 'error' && (
        <p>{result.msg}</p>
      )}

      {result && !result.type && (
        <>
          <p>이름: {result.name}</p>
          <p>나이: {result.age}</p>
        </>
      )}
    </div>
  )
}

export default UseForm