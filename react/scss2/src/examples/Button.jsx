import React from 'react'
import './Button.scss'

function Button({ children, variant='primary', size='medium', onClick, disabled=false }) {
  return (
    <button
      className={`btn btn--${variant} btn--${size}`}
      onClick={onClick}
      disabled={disabled}
      >
        {children}
    </button>
  )
}

export default Button
//children - 버튼안에 들어갈 내용
//variant - 버튼의 스타일을 결정하는 속성(primary, secondary, danger 등)
//size - 버튼의 크기
//onClick - 버튼이 클릭될 때 실행되는 함수(클릭 이벤트)
//disabled - 버튼이 비활성화될 때 true로 설정되는 속성