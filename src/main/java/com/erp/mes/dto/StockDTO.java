package com.erp.mes.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@Data
public class StockDTO {
    private int stk_id;        // 재고번호
    private int qty;           // 재고수량
    private String loc;        // 재고위치
    private double value;      // 재고금액
    private String status;     // 재고상태
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date in_date;      // 입고일자
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exp_date;     // 유효기간
    private int cons_qty;      // 소모수량
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cons_date;    // 소모일자
    private String cons_loc;   // 소모위치

}