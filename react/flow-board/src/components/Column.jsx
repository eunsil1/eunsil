import React from 'react'
import { useState } from 'react'
import Card from './Card';

//컬럼 하나를 랜더링합니다. 헤더(제목, 카드개수), 카드 목록, 카드 추가 버튼 / 폼을 포함합니다.

function Column({column, cards, onDeleteCard, onUpdateCard, onDragStart, onDragEnd, isDragging, onAddCard}) {
  const [isAdding, setIsAdding] = useState(false);

  const handleAdd = (text) => {
    onAddCard(column.id, text)
    setIsAdding(false);
  }

  return (
    <section className='column' style={{'--column-accent':Column.color}}>
      <div className="column-header">
        <h2 className="column-title">{column.title}</h2>
        <span className="column-count">{column.length}</span>
      </div>
      <div className={`column-cards ${isDragging ? 'dragging' : ''}`}>
        {cards.map((card)=>{
          <Card
            key={card.id}
            card={card}
            onDelete={onDeleteCard}
            onUpdate={onUpdateCard}
            onDragStart={onDragStart}
            onDragEnd={onDragEnd}
          />
        })}
        
      </div>
      {isAdding ? (
        <CardForm
          onSubmit={handleAdd}
          onCancel={() => setIsAdding(false)}
          placeholder="작업 내용 입력..."
        />
        ) : (
        <button
          className="add-btn"
          onClick={() => setIsAdding(true)}
          aria-label="카드 추가"
        >
          + 카드 추가
        </button>
      )}

    </section>
  )
}

export default Column