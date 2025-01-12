package com.erp.mes.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ItemDTO {
    private int item_id;        // 품목번호, 기본 키
    private String type;           // 품목 유형
    private String name;           // 품목명
    private String spec;           // 규격
    private String unit;           // 단위
    private BigDecimal price;      // 단가
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date; // 품목 등록일자

}
