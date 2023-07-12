package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.TrxOrderAdditionalItem;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrxOrderAdditionalItemRepository extends PagingAndSortingRepository<TrxOrderAdditionalItem, String>, QuerydslPredicateExecutor<TrxOrderAdditionalItem> {
}
