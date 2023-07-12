package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.TrxOrderCustomerInfo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxOrderCustomerInfoRepository extends PagingAndSortingRepository<TrxOrderCustomerInfo, String>, QuerydslPredicateExecutor<TrxOrderCustomerInfo> {
}
