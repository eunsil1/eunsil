import React from 'react'
import { EMOTIONS } from '../constants'


function EmotionPicker({value, onChange}) {
  return (
    <div className='emotion-picker'>
      <span className='emotion-label'>오늘의 감정</span>
      <div className="emotion-grid">
        {EMOTIONS.map((emotion)=>(
        <button
          key={emotion.id}
          type='button'
          className={`emotion-btn ${value === emotion.id ? 'selected' : ''}`}
          onClick={() => onChange(emotion.id)}
          title={emotion.label}
          style={{
            '--emotion-color': emotion.color,
          }}
        >
          <span className='emotion-emoji'>{emotion.emoji}</span>
          <span className='emotion-text'>{emotion.label}</span>
        </button>
        ))}
      </div>
    </div>
  )
}

export default EmotionPicker