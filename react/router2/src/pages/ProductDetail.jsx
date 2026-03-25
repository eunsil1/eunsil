import React from 'react'
import products from '../data/products'
import {useParams, useNavigate} from 'react-router-dom'

function ProductDetail() {
  const {id} = useParams(); //URL 주소에서 id 가져올 수 있음
  const navigate = useNavigate();

  const product = products.find((item) =>  item.id === parseInt(id))
  if(!product){
    return <h3>상품이 없습니다.</h3>
  }


  return (
    <div>
      <h2>상품 상세</h2>
      <p>상품명: {product.name}</p>
      <p>가격: {product.price}원</p>
      <button onClick={()=>navigate(-1)}>뒤로가기</button>
    </div>
  )
}

export default ProductDetail