package com.shop.exception;

public class OutOfStockException extends RuntimeException{

    //주문하면 현재 상품의 재고에서 주문 수량만큼 재고 감소
    //주문수량보다 재고의 수가 적을때 발생 시킬 예외를 정의
    public OutOfStockException(String message){
        super(message);
    }
}
