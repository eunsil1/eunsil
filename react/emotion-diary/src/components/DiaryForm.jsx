import React from 'react'
import EmotionPicker from './EmotionPicker'
import {useState} from 'react'

function DiaryForm({onSubmit}) {
  const [emotion, setEmotion] = useState(''); //emotion -> 선택한 감정
  const [content, setContent] = useState(''); //content -> 입력한 글

  const handleSubmit = (e) => {
    e.preventDefault();
    if(!emotion || !content.trim()) return; //감정 선택 X 내용 X
    onSubmit({emotion, content: content.trim()})
    //부모에게 데이터 전달 DiaryForm -> App.addEntry()
    setEmotion('');
    setContent('');
  }

  return (
    <form className='diary-form' onSubmit={handleSubmit}>
      <EmotionPicker value={emotion} onChange={setEmotion}/>
      <div className='form-group'>
        <textarea
          placeholder='오늘 하루는 어땠나요? 마음을 적어보세요...'
          value={content}
          onChange={(e) => setContent(e.target.value)}
          rows={5}
        />
      </div>
      <button type='submit' disabled={!emotion || !content.trim()} className='submit-btn'>
        일기 저장하기
      </button>
    </form>
  )
}

export default DiaryForm