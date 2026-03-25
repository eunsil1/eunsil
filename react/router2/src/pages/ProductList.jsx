import React from 'react'
import products from '../data/products'
import {Link} from 'react-router-dom'

function ProductList() {
  return (
    <div>
      <h2>상품목록</h2>
      <ul>
      {products.map((item)=>(  
        <li key={item.id}>
          {item.name} - {item.price} 원
          <Link to={`/product/${item.id}`} style={{marginLeft:"18px"}}>
          상세보기
          </Link>
        </li>
      ))}
      </ul> 
    </div>
  )
}

export default ProductList