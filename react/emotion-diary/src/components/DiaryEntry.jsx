import React from 'react'
import { EMOTIONS } from '../constants'

//entry 하나를 받아서 감정 + 날짜 + 내용 + 삭제 버튼을 보여주는 컴포넌트
function DiaryEntry({entry, onDelete}) {
  const emotionData = EMOTIONS.find((e) => e.id === entry.emotion)
  //현재 일기의 감정 정보를 가져오기
  //entry.emotion -> happy(예) 행복선택시)
  //EMOTIONS=[{ id: 'happy', label: '행복', emoji: '😊', color: '#fcd34d' },...]
  //emotionData = { id: 'happy', label: '행복', emoji: '😊', color: '#fcd34d'}
  //색상, 이미지, 이름
  const formatDate = (timestamp)  => { //시간을 '오늘/날짜' 형태로 바꿔줌
    const d = new Date(timestamp)
    const now = new Date();
    const isToday = d.toDateString() === now.toDateString(); //같은 날짜면 -> 오늘
      return isToday
      ? `오늘 ${d.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' })}` //오늘 시간과 분만 표시
      : d.toLocaleDateString('ko-KR', {
          month: 'long',
          day: 'numeric',
          hour: '2-digit',
          minute: '2-digit',
          //오늘이 아니면 월, 일, 시간, 분
        });
  }

  return (
    <article className='diary-entry'>
      <div className="entry-header">
        <span className='entry-emotion'
          style={{backgroundColor: emotionData?.color || '#e5e7eb'}}
          //색이 없으면 회색
          title={emotionData?.label}
        >
          {emotionData?.emoji || '📝'}
          {/* ?. 안전접근 (없어도 에러 안남) */}
        </span>
        <span className="entry-date">{formatDate(entry.createdAt)}</span>
        <button
          type='button'
          className='entry-delete'
          onClick={()=> onDelete(entry.id)}
          title='삭제'
        >
          X
        </button>
      </div>
      <p className='entry-content'>{entry.content}</p>
    </article>
  )
}

export default DiaryEntry