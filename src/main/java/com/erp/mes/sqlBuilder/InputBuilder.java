package com.erp.mes.sqlBuilder;

import com.erp.mes.dto.InputDTO;
import com.erp.mes.dto.TransactionDTO;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.ws.soap.SoapElement;

import java.util.Map;

public class InputBuilder {
    private static final String MAX_NUMBER = "100";
    private static final Integer MIN_NUMBER =0;
    public String buildSelectInput() {
        return new SQL() {{
            SELECT("input_id as inputId,input.type,rec_date as recDate,supplier.name as supName,inventory.name as invenName,item.name as itemName,plan.qty ");
            FROM("`input`");
            JOIN("transaction on transaction.tran_id = `input`.tran_id");
            JOIN("supplier on supplier.sup_code = transaction.sup_code");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("item on item.item_id = plan.item_id");
            JOIN("inventory on inventory.inven_id = supplier.inven_id");
            WHERE("input.type = 0");
        }}.toString();
    }
    public String buildUpdateInput(InputDTO inputDTO) {
        return new SQL() {{
            UPDATE("input");
            JOIN("transaction on transaction.tran_id = `input`.tran_id");
            JOIN("supplier on supplier.sup_code = transaction.sup_code");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("item on item.item_id = plan.item_id");
            JOIN("inventory on inventory.inven_id = supplier.inven_id");
            if(inputDTO.getSupName() != null) {
                SET("supplier.name = #{supName}");
            }
            if(inputDTO.getInvenName() != null) {
                SET("inventory.name = #{invenName}");
            }
            if(inputDTO.getItemName() != null) {
                SET("item.name = #{itemName}");
            }
            if(inputDTO.getQty()!= MIN_NUMBER) {
                SET("plan.qty = #{qty}");
            }
            WHERE("input.input_id = #{inputId}");
        }}.toString();
    }

    public String buildSelectTransaction() {
        return new SQL(){{
            SELECT("transaction.date, transaction.val,`input`.exp_date,plan.qty,item.name,item.price,item.unit");
            FROM("transaction");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("`input` on `input`.tran_id = transaction.tran_id");
            JOIN("item on item.item_id = plan.item_id");
        }}.toString();
    }
    public String buildUpdateTransaction(TransactionDTO transactionDTO){
        return new SQL(){{
            UPDATE("transaction");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("input on input.tran_id = transaction.tran_id");
            JOIN("item on item.item_id = plan.item_id");

            if (transactionDTO.getDate() != null) {
                SET("transaction.date = #{date}");
            }
            if (transactionDTO.getExpDate() != null) {
                SET("input.exp_date = #{expDate}");
            }
            if(transactionDTO.getQty() != MIN_NUMBER) {
                SET("plan.qty = #{qty}");
            }

            if(transactionDTO.getItemName() != null) {
                SET("item.name = #{itemName}");
                SET("item.price = #{price}");
                SET("item.unit = #{unit}");
                SET("transaction.val = #{price} * #{qty}");
            }
            WHERE("transaction.tran_id = #{tranId}");
        }}.toString();
    }
    public String buildInsertOrder() {
        return new SQL(){{
            INSERT_INTO("`order`");
            VALUES("order_code","#{orderCode}");
            VALUES("date","#{date}");
            VALUES("status","'진행중'");
            VALUES("value","#{value}");
            VALUES("sup_id","#{supId}");
            VALUES("plan_id","#{planId}");
        }}.toString();
    }

    public String buildSelectOrder() {
        return new SQL(){{
            SELECT("order.date, order.leadtime,order.status,order.value,item.name,item.price,item.unit");
            FROM("order");
            JOIN("item on item.item_id = `order`.item_id");
        }}.toString();
    }

    public String buildUpdateOrder() {
        return new SQL(){{
            UPDATE("order");
            SET("status = #{status}");
            WHERE("order_code = #{order_code}");
        }}.toString();
    }
    public String buildUpdateInputStatus(Map<String,Object> map) {
        return new SQL() {{
            if(map.get("selectValue").equals("완료")) {
                UPDATE("input");
                SET("type = 1");
                WHERE("input_id = #{inputId}");
            }else {
                UPDATE("input");
                SET("type = 0");
                WHERE("input_id = #{inputId}");
            }
        }}.toString();
    }
    public String buildSearch(InputDTO inputDTO) {
        return new SQL(){{
            SELECT("input_id as inputId,input.type,rec_date as recDate,supplier.name as supName,inventory.name as invenName,item.name as itemName,plan.qty ");
            FROM("`input`");
            JOIN("transaction on transaction.tran_id = `input`.tran_id");
            JOIN("supplier on supplier.sup_code = transaction.sup_code");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("item on item.item_id = plan.item_id");
            JOIN("inventory on inventory.inven_id = supplier.inven_id");
            if(inputDTO.getKeyword() != null) {
                WHERE("supplier.name like concat('%', #{keyword}, '%')");
                OR();
                WHERE("inventory.name like concat('%', #{keyword}, '%')");
                OR();
                WHERE("item.name like concat('%', #{keyword}, '%')");
            }
        }}.toString();
    }
    public String buildPaging(Map<String,Object> map) {
        return new SQL(){{
            SELECT("input_id as inputId,input.type,rec_date as recDate,supplier.name as supName,inventory.name as invenName,item.name as itemName,plan.qty ");
            FROM("`input`");
            JOIN("transaction on transaction.tran_id = `input`.tran_id");
            JOIN("supplier on supplier.sup_code = transaction.sup_code");
            JOIN("plan on plan.plan_id = transaction.plan_id");
            JOIN("item on item.item_id = plan.item_id");
            JOIN("inventory on inventory.inven_id = supplier.inven_id");
            ORDER_BY("input_id desc");
            LIMIT("#{start} , #{limit}");
        }}.toString();
    }
    public String buildPageCount() {
        return new SQL(){{
            SELECT("count(input_id)");
            FROM("`input`");
        }}.toString();
    }
}