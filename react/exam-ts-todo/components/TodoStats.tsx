import React from 'react'
import { countByDone } from '../src/lib/todoQueries';
import type {Todo} from '../src/types/todo';

export interface TodoStatsProps {
  todos: Todo[];
}

function TodoStats({todos}: TodoStatsProps) {
  const {active, completed} = countByDone(todos); //구조분해 할당
  return (
    <p className='stats'>
      남은 할 일 <strong>{active}</strong>개 , 완료 <strong>{completed}</strong>개
    </p>
  )
}

export default TodoStats