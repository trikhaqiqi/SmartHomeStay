package com.codingtest.smarthome.integrations.midtrans.request_payment.services;


import com.codingtest.smarthome.integrations.midtrans.request_payment.forms.IntegrationMidtransRequestPaymentForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;

public interface IntegrationMidtransRequestPaymentService {

    IntegrationMidtransGetSnapResponse call(IntegrationMidtransRequestPaymentForm form);

}
