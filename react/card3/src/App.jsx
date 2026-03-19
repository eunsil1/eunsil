import React from 'react'
import Card from "./Card1";
import "./App.css";

const cardsData = [
  {
    id: 1,
    title: '봄날의 꽃',
    content: '따뜻한 봄바람에 흩날리는 꽃잎. 계절의 아름다움을 담았습니다.',
    imageUrl: 'https://picsum.photos/300/200?random=1',
    backgroundColor: '#ffebee'
  },
  {
    id: 2,
    title: '숲속의 오솔길',
    content: '푸른 나무 사이로 이어진 작은 길. 자연의 여유를 느껴보세요.',
    imageUrl: 'https://picsum.photos/300/200?random=2',
    backgroundColor: '#e8f5e8'
  },
  {
    id: 3,
    title: '바다의 파도',
    content: '푸른 바다와 하늘. 시원한 바닷바람이 상쾌함을 선사합니다.',
    imageUrl: 'https://picsum.photos/300/200?random=3',
    backgroundColor: '#e3f2fd'
  },
  {
    id: 4,
    title: '저녁 노을',
    content: '하늘을 물들인 석양. 하루를 마무리하는 평화로운 순간입니다.',
    imageUrl: 'https://picsum.photos/300/200?random=4',
    backgroundColor: '#f3e5f5'
  },
  {
    id: 5,
    title: '따뜻한 커피',
    content: '향긋한 커피 한 잔. 여유로운 오후를 위한 작은 선물.',
    imageUrl: 'https://picsum.photos/300/200?random=5',
    backgroundColor: '#fff3e0'
  },
  {
    id: 6,
    title: '맑은 호수',
    content: '잔잔한 물결과 투명한 물. 마음이 차분해지는 풍경입니다.',
    imageUrl: 'https://picsum.photos/300/200?random=6',
    backgroundColor: '#e0f2f1'
  },
  {
    id: 7,
    title: '정원의 나비',
    content: '꽃 위를 날아다니는 나비. 작은 생명의 아름다움입니다.',
    imageUrl: 'https://picsum.photos/300/200?random=7',
    backgroundColor: '#fce4ec'
  },
  {
    id: 8,
    title: '별이 빛나는 밤',
    content: '고요한 밤하늘의 별들. 꿈꾸는 시간을 선사합니다.',
    imageUrl: 'https://picsum.photos/300/200?random=8',
    backgroundColor: '#f5f5f5'
  },
]

function App() {
  return (
    <div className='App'>
      <header className='App-header'>
        <h1>8장의 카드</h1>
        <p>자연과 일상의 아름다운 순간들</p>
      </header>
      <main className='App-main'>
        <div className="cards-container">
          {cardsData.map((card) => (
            <Card
            key={card.id}
            title={card.title} 
            content={card.content}
            imageUrl={card.imageUrl}
            backgroundColor={card.backgroundColor}
            />
          ))}
        </div>
      </main>
    </div>
  )
}

export default App