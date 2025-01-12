package com.erp.mes.mapper;

import com.erp.mes.dto.InspectionDTO;
import com.erp.mes.dto.OrderDTO;
import com.erp.mes.dto.PlanDTO;
import com.erp.mes.sqlBuilder.PurchaseBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PurchaseMapper {

    @SelectProvider(type = PurchaseBuilder.class, method = "selectPlans")
    List<PlanDTO> plan(); // 조달 계획 리스트

    @InsertProvider(type = PurchaseBuilder.class,method = "insertOrder")
    int orderCreate(Map<String, Object> map); // 구매 발주서 발행

    @SelectProvider(type = PurchaseBuilder.class,method = "selectOrders")
    List<OrderDTO> order(); // 구매 발주서 리스트 - 필수 값 -> 납기, 협력회사 이름

    @UpdateProvider(type = PurchaseBuilder.class, method = "updateOrder")
    int orderForm(Map<String, Object> map); // 구매 발주서 수정 - 납기, 상태, 금액 부분 변경 - 이외 사항 발주 새로 발행

    @SelectProvider(type = PurchaseBuilder.class, method = "selectInspections")
    int inspection(); // 검수 확인

    @InsertProvider(type = PurchaseBuilder.class,method = "insertInspection")
    int inspectionForm(Map<String, Object> map); // 검수 생성

    @UpdateProvider(type = PurchaseBuilder.class, method = "updateInspection")
    int inspectionUpdate(Map<String, Object> map); // 검수 업데이트


}
