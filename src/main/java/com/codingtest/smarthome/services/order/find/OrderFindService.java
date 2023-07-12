package com.codingtest.smarthome.services.order.find;

import com.codingtest.smarthome.dto.models.TrxOrderDto;
import com.codingtest.smarthome.dto.queryfilters.OrderQueryFilter;
import org.springframework.data.domain.Page;

public interface OrderFindService {

    Page<TrxOrderDto> call(OrderQueryFilter qf);

}
