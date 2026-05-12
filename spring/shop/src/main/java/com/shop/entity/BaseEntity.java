package com.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//시간만 상속 하고 싶으면 BaseTimeEntity
//시간과 작성자 정보를 다 기록하고 싶으면 BaseEntity
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity{
    //CreatedDate가 시스템의 시간을 가져온다면
    @CreatedBy //@CreatedBy 현재 로그인한 사용자의 아이디를 주입
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
//itemRepository.save(item)
//createdBy = 'admin@test.com'
// modifiedBy = 'admin@test.com'
