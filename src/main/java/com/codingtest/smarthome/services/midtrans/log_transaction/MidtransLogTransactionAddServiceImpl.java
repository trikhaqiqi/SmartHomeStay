package com.codingtest.smarthome.services.midtrans.log_transaction;

import com.codingtest.smarthome.dto.forms.MidtransLogTransactionForm;
import com.codingtest.smarthome.models.TrxOrder;
import com.codingtest.smarthome.models.midtrans.MidtransLogTransaction;
import com.codingtest.smarthome.models.midtrans.MidtransTrxRequestPayment;
import com.codingtest.smarthome.repositories.MidtransLogTransactionRepository;
import com.codingtest.smarthome.repositories.MidtransTrxRequestPaymentRepository;
import com.codingtest.smarthome.repositories.TrxOrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MidtransLogTransactionAddServiceImpl implements MidtransLogTransactionAddService{

    private static final Logger LOG = LogManager.getLogger(MidtransLogTransactionAddServiceImpl.class);

    @Autowired
    MidtransLogTransactionRepository midtransLogTransactionRepository;

    @Autowired
    MidtransTrxRequestPaymentRepository midtransTrxRequestPaymentRepository;

    @Autowired
    TrxOrderRepository trxOrderRepository;

    @Override
    public void call(MidtransLogTransactionForm form) {
        LOG.info("start add log midtrans transaction");

        MidtransLogTransaction midtransLogTransaction = MidtransLogTransaction.convertFrom(form);

        midtransLogTransactionRepository.save(midtransLogTransaction);

        // cek ke midtrans payment request, dan ambil order id nya
        MidtransTrxRequestPayment midtransTrxRequestPayment = midtransTrxRequestPaymentRepository.findByMidtrans_order_id(midtransLogTransaction.getOrder_id());
        if (midtransTrxRequestPayment != null) {
            // kalo status dari midtrans adalah "settlement" artinya sudah bayar yaa
            if ("settlement".equalsIgnoreCase(midtransLogTransaction.getTransaction_status()) || "success".equalsIgnoreCase(midtransLogTransaction.getTransaction_status()) ||
                "capture".equalsIgnoreCase(midtransLogTransaction.getTransaction_status())){
                // kalo misal di temukan, update status order nya jadi new, karena sebelumnya jadi cancel (ini untuk keperluan mobile)
                TrxOrder order = trxOrderRepository.findById(midtransTrxRequestPayment.getOrder_id()).orElse(null);
                if (order != null) {
                    order.setIsPaid(true);
                    order.setMidtransOrderId(midtransLogTransaction.getOrder_id());

//                    Runnable r = () -> sendEmailSuccess(order);
//                    new Thread(r).start();
                    trxOrderRepository.save(order);
                }
            }
        }

        LOG.info("finish add log midtrans transaction");
    }
}
