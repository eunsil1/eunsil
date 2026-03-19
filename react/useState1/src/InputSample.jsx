import { useState } from "react"


function InputSample() {
  const [inputs, setInputs] = useState({
    name:'',
    nickname:'',
    phone:''
  });
  const {name,nickname,phone} = inputs; //비구조화 할당을 통해 값 추출
  
  const onChange = (e) => {
    const {value, name} = e.target; //우선 e.target에서 name과 value를 추출
    setInputs({
      ...inputs, //기존의 input 객체를 복사한 뒤
      [name]:value //name 키를 가진 값을 value로 설정

    });
  } 
  const onReset = () => {
    setInputs({
      name:'',
      nickname:'',
      phone:''
    });
  }

  return (
    <div>
      <input name="name" placeholder="이름" onChange={onChange} value={name}/>
      <input name="nickname" placeholder="닉네임" onChange={onChange} value={nickname}/>
      <input name="phone" placeholder="전번" onChange={onChange} value={phone}/>
      <button onClick={onReset}>초기화</button>
      <div>
        <b>값: </b>
        {name}({nickname})-{phone}
      </div>
    </div>
  )
}

export default InputSample