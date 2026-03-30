import './TodoItem.css'


interface TotoItemProps{
    //데이터(할 일의 정보)
  id: number;
  content: string;
  isDone: boolean;
  createdDate: number;
//함수 (부모와 소통하느 통로)
  onUpdate: (id: number) => void; // 숫자 id 하나 받아서 아무거도 반환 하지 안는 함수(void)
  //체크박스 클릭하면 부모의 unUpdate를 실행
  onDelete: (id: number) => void;
}

export default function TodoItem({id, content,isDone,createdDate,onUpdate, onDelete}:TotoItemProps) {
//  구조분해 할당 onupdate 추가 
 
  const onChangeCheckbox = ()=>{
    onUpdate(id);
  };
  const onClickDelete =()=> {
    onDelete(id);
  };   


  return (
    <div className='TodoItem'>
        <div className="checkbox_col"> 
            <input type="checkbox" checked={isDone} 
            onChange={onChangeCheckbox}
            // 체크했을때 호출할 함수 onChangeCheckbox는 
            //onUpadte 호출하고 인수는 현재 클릭한 할일 아이템 id전달
            />
        </div>
        <div className="title_col">{content}</div>
        <div className="date_col">
            {new Date(createdDate).toLocaleDateString()}</div>
        <div className="btn_col">
            <button onClick={onClickDelete}>삭제</button>
        </div>
    </div>
  )
}
