package com.codingtest.smarthome.services.order.add;

import com.codingtest.smarthome.dto.forms.OrderAddForm;
import com.codingtest.smarthome.models.TrxOrder;
import com.codingtest.smarthome.services.order.add.logic.OrderAddLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderAddLogic orderAddLogic;

    @Override
    public TrxOrder add(OrderAddForm form) {
        return orderAddLogic.call(form);
    }


}
