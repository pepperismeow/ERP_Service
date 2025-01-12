package com.erp.mes.service;

import com.erp.mes.dto.InputDTO;
import com.erp.mes.dto.OrderDTO;
import com.erp.mes.dto.TransactionDTO;
import com.erp.mes.mapper.InputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InputService {
    private final InputMapper mapper;
    public List<InputDTO> inputList() {
        return mapper.inputList();
    }
    public int inputForm() {
        return mapper.inputForm();
    }
    public List<TransactionDTO> transactionList() {
        return mapper.transactionList();
    }
    public int transactionFrom(Map<String,Object> map) {
        return mapper.transactionForm(map);
    }
    public List<OrderDTO> orderList() {
        return mapper.orderList();
    }
    public int orderForm(Map<String,Object> map) {
        return mapper.orderForm(map);
    }

}
