package com.shop.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.dto.QMainItemDto;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import com.shop.entity.QItemImg;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    //queryDsl 쿼리를 실행할 객체 생성
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    //BooleanExpression - 동적 쿼리를 위한 메서드 searchSellStatus - 판매 상태가 (판매중 / 품절)
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();
        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        }else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1); //하루 조회
        }else if(StringUtils.equals("1w",searchDateType)){
            dateTime = dateTime.minusWeeks(1); //일주일
        }else if(StringUtils.equals("1m",searchDateType)){
            dateTime = dateTime.minusMonths(1); //한달
        }else if(StringUtils.equals("6m",searchDateType)){
            dateTime = dateTime.minusMonths(6); //6개월
        }
        return QItem.item.regTime.after(dateTime);

    }

    private  BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("itemNm", searchBy)){
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        }else if(StringUtils.equals("createdBy", searchBy)){
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(QItem.item) //item 테이블로부터 모든 컬럼을 조회(select * from item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()) //등록 날짜 조건
                        , searchSellStatusEq(itemSearchDto.getSearchSellStatus()) //판매 상태 조건
                        , searchByLike(itemSearchDto.getSearchBy()  //검색어 조건(상품명 또는
                                , itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc()) //id 역순으로 정렬
                .offset(pageable.getOffset()) //데이터를 가져올 시작위치 (2페이지면 10번부터)
                .limit(pageable.getPageSize()) //한페이지를 보여줄 데이터 개수 (예:10개씩)
                .fetch(); //쿼리를 실행하고 결과를 리스트로 반환함

        //페이징 내비게이션 [이전][1][2][3][다음] 만들려면 total 값이 있어야 가능
        long total = queryFactory
                .select(Wildcard.count)//item 테이블로부터 모든 컬럼을 조회(select * from item)
                .from(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()) //등록 날짜
                        , searchSellStatusEq(itemSearchDto.getSearchSellStatus())
                        , searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne(); //count 쿼리는 결과가 항상 숫자 하나
        //select count(*)
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression itemNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%"+searchQuery+"%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;
        //메인 상품 목록을 DTO형태로 조회하는 쿼리
        List<MainItemDto> content = queryFactory
                .select(
                        new QMainItemDto(
                                item.id, //상품 id
                                item.itemNm, //상품명
                                item.itemDetail, //상품설명
                                itemImg.imgUrl, //대표이미지
                                item.price //가격
                        )
                )
                .from(itemImg) //기준 테이블 - 대표이미지가 필요하기 때문
                .join(itemImg.item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(Wildcard.count)//item 테이블로부터 모든 컬럼을 조회(select * from item)
                .from(itemImg)
                .join(itemImg.item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .fetchOne(); //count 쿼리는 결과가 항상 숫자 하나
        //select count(*)
        return new PageImpl<>(content, pageable, total);
    }

}
