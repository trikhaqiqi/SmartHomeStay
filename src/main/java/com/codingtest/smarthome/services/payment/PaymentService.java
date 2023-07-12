package com.codingtest.smarthome.services.payment;


import com.codingtest.smarthome.dto.forms.OrderPaymentRequestForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;

public interface PaymentService {

    IntegrationMidtransGetSnapResponse requestMidtransLink(OrderPaymentRequestForm form);

}
