import React from 'react'
import './Card1.css';

function Card1({title,content,imageUrl,backgroundColor}) {
  return (
    <div className='card' style={{backgroundColor:backgroundColor}}>
      <div className='card-image'>
        <img src={imageUrl} alt={title} />
      </div>
      <div className='card-content'>
        <h3 className='card-title'>{title}</h3>
        <p className='card-text'>{content}</p>
      </div>
    </div>
  )
}

export default Card1