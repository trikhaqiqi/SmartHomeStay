package com.codingtest.smarthome.integrations.midtrans.request_payment.services;

import com.codingtest.smarthome.integrations.midtrans.request_payment.forms.IntegrationMidtransRequestPaymentForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;
import com.midtrans.Midtrans;
import com.midtrans.httpclient.SnapApi;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class IntegrationMidtransRequestPaymentServiceImpl implements IntegrationMidtransRequestPaymentService{

    private static final Logger LOG = LogManager.getLogger(IntegrationMidtransRequestPaymentServiceImpl.class);

    @Value("${app.integration.midtrans.server-key}")
    private String serverKey;

    @Value("${app.integration.midtrans.url.vtweb}")
    private String urlVtWeb;

    @Value("${app.integration.midtrans.is-production}")
    private String isProduction;

    @SneakyThrows
    @Override
    public IntegrationMidtransGetSnapResponse call(IntegrationMidtransRequestPaymentForm form) {
        Boolean isProductionStatus = false;

        if ("true".equals(isProduction)){
            isProductionStatus = true;
        }

        Midtrans.serverKey = serverKey;
        Midtrans.isProduction = isProductionStatus;

        String idRand = UUID.randomUUID().toString();
        Map<String, Object> params = new HashMap<>();

        Map<String, String> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", idRand);
        transactionDetails.put("gross_amount", "" + form.getGross_amount());

        Map<String, String> creditCard = new HashMap<>();
        creditCard.put("secure", "true");

        Map<String, String> customerDetails = new HashMap<>();
        customerDetails.put("first_name", form.getFirst_name());
        customerDetails.put("last_name", form.getLast_name());
        customerDetails.put("email", form.getEmail());
        customerDetails.put("phone", form.getPhone());

        params.put("transaction_details", transactionDetails);
        params.put("credit_card", creditCard);
        params.put("customer_details", customerDetails);

        String transactionToken = SnapApi.createTransactionToken(params);

        String vtwebUrl = urlVtWeb + transactionToken;
        IntegrationMidtransGetSnapResponse integrationMidtransGetSnapResponse = new IntegrationMidtransGetSnapResponse()
                .setToken(transactionToken).setRedirect_url(vtwebUrl).setOrder_id(idRand);

        LOG.info(transactionToken);
        return integrationMidtransGetSnapResponse;
    }
}
