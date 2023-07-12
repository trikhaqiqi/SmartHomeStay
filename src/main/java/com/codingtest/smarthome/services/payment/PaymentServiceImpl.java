package com.codingtest.smarthome.services.payment;

import com.codingtest.smarthome.dto.forms.OrderPaymentRequestForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;
import com.codingtest.smarthome.services.payment.logic.PaymentRequestMidtransLinkLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    private static final Logger LOG = LogManager.getLogger(PaymentServiceImpl.class);

    @Autowired
    PaymentRequestMidtransLinkLogic paymentRequestMidtransLinkLogic;

    @Override
    public IntegrationMidtransGetSnapResponse requestMidtransLink(OrderPaymentRequestForm form) {
        return paymentRequestMidtransLinkLogic.call(form);
    }
}
