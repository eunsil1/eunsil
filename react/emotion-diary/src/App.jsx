import React from 'react'
import {useState, useEffect} from 'react'
import DiaryForm from './components/DiaryForm';
import DiaryList from './components/DiaryList';


const STORAGE_KEY = 'emotion-diary-entries'
// 저장된 일기 불러오기
function loadEntries( ){
  try{
    const raw = localStorage.getItem(STORAGE_KEY);
    return raw ? JSON.parse(raw) : [] //json -> 객체로 변환 없으면 빈배열 반환
  }catch{
    return [];
    //json 깨졌을때 앱 터지는 거 방지
  }
} 

function saveEntries(entries){
  localStorage.setItem(STORAGE_KEY, JSON.stringify(entries));
  //객체 -> json 변환 저장
}

function App() {
  //처음 실행 시 loadEntries() 실행 -> 기존데이터가 있으면 불러옴
  const [entries, setEntries] = useState(loadEntries);

  useEffect(()=> {
    saveEntries(entries); //자동으로 loadEntries에 저장
  },[entries]);//entries가 바뀔때마다 실행됨
  //entries 변경 -> useEffect 실행 -> 저장

  const addEntry = (data) => {
    const entry = {
      id: crypto.randomUUID(), //고유 id 생성
      emotion: data.emotion,
      content: data.content,
      createdAt: Date.now(),
    }
    setEntries((prev)=> [entry,...prev]) //새 데이터 앞에 추가
  }
  const deleteEntry =(id) =>{
    setEntries((prev)=> prev.filter((e)=>e.id!==id))
  }
  //filter로 해당 id 제외 -> 새로운 배열을 반환
  //기존배열 수정 X 새배열이 생성 -> React 불변성
  return (
    <div className='app'>
      <header className="header">
        <h1>
          <span className="header-icon">📔</span>
          <p className="tagline">오늘의 감정을 기록하고 마음을 정리해보세요</p>
        </h1>
      </header>
      <main className="main">
        <section className='write-section'>
          <h2>오늘의 일기</h2>
          <DiaryForm onSubmit={addEntry}/>
        </section>

        <section className="history-section">
          <h2>나의 일기 모음</h2>
          <DiaryList entries={entries} onDelete={deleteEntry}/>
        </section>
      </main>
    </div>
  )
}

export default App