package com.codingtest.smarthome.services.order.find;

import com.codingtest.smarthome.dto.models.TrxOrderDto;
import com.codingtest.smarthome.dto.queryfilters.OrderQueryFilter;
import com.codingtest.smarthome.models.TrxOrder;
import com.codingtest.smarthome.models.QTrxOrder;
import com.codingtest.smarthome.utils.CustomPageImpl;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFindServiceImpl implements OrderFindService {

    private static final Logger LOG = LogManager.getLogger(OrderFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<TrxOrderDto> call(OrderQueryFilter qf) {

        LOG.info("start find order");

        List<TrxOrderDto> dtoList = new ArrayList<>();

        QTrxOrder qTrxOrder = QTrxOrder.trxOrder;

        JPAQuery<TrxOrder> query = new JPAQuery<TrxOrder>(entityManager).from(qTrxOrder)
                        .limit(qf.getLength()).offset(qf.getStart());

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qTrxOrder.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getUser_id())) {
            query.where(qTrxOrder.user_id.eq(qf.getUser_id()));
        }

        QueryResults<TrxOrder> results = query.fetchResults();

        dtoList = results.getResults().stream()
                        .map(TrxOrder::toDto)
                                .collect(Collectors.toList());

        LOG.info("finish find order");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }
}
