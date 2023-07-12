package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.midtrans.MidtransTrxRequestPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidtransTrxRequestPaymentRepository extends PagingAndSortingRepository<MidtransTrxRequestPayment, String>, QuerydslPredicateExecutor<MidtransTrxRequestPayment> {

    @Query(value = "SELECT * FROM midtrans_trx_request_payments where midtrans_token_id = ?1 LIMIT 1", nativeQuery = true)
    MidtransTrxRequestPayment findByMidtrans_token_id(String midtransTokenId);

    @Query(value = "SELECT * FROM midtrans_trx_request_payments where midtrans_order_id = ?1 LIMIT 1", nativeQuery = true)
    MidtransTrxRequestPayment findByMidtrans_order_id(String midtransOrderId);

}
