package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.TrxOrderItem;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxOrderItemRepository extends PagingAndSortingRepository<TrxOrderItem, String>, QuerydslPredicateExecutor<TrxOrderItem> {
}
