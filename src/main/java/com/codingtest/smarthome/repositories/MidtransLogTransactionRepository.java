package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.midtrans.MidtransLogTransaction;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidtransLogTransactionRepository extends PagingAndSortingRepository<MidtransLogTransaction, String>, QuerydslPredicateExecutor<MidtransLogTransaction> {
}
