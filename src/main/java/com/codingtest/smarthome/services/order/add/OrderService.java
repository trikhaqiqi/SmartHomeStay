package com.codingtest.smarthome.services.order.add;

import com.codingtest.smarthome.dto.forms.OrderAddForm;
import com.codingtest.smarthome.models.TrxOrder;

public interface OrderService {

    TrxOrder add(OrderAddForm form);

}
