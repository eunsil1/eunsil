import React from 'react'
import { countByDone } from '../src/lib/todoQueries';

function TodoStats({todos}) {
  const {active, completed} = countByDone(todos); //구조분해 할당
  return (
    <p className='stats'>
      남은 할 일 <strong>{active}</strong>개 , 완료 <strong>{completed}</strong>개
    </p>
  )
}

export default TodoStats