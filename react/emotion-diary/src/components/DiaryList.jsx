import React from 'react'
import DiaryEntry from './DiaryEntry'

function DiaryList({entries, onDelete}) {
  //entries: 전체 데이터 배열 onDelete: 삭제함수
  if(entries.length === 0){ //데이터가 없으면
    return( //안내 UI
      <div className='diary-empty'>
          <p className='empty-emoji'>📔</p>
          <p>아직 작성한 일기가 없어요</p>
          <p>첫 번째 감정을 기록해보세요</p>
      </div>
    );
  }
  return (
    <div className='diary-list'>
      {entries
        .slice() //React 불변성 중요 - 복사(사본) 후 
        .sort((a,b) => b.createdAt - a.createdAt) //정렬 - 정렬만 했을 시 원본 배열이 바뀜
        //최신글을 위로
        .map((entry)=>(
          <DiaryEntry
            key={entry.id}
            entry={entry}
            onDelete={onDelete}
          />
        
        ))}
    </div>
  )
}

export default DiaryList