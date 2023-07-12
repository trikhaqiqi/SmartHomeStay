package com.codingtest.smarthome.services.payment.logic;

import com.codingtest.smarthome.dto.forms.OrderPaymentRequestForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.forms.IntegrationMidtransRequestPaymentForm;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;
import com.codingtest.smarthome.integrations.midtrans.request_payment.services.IntegrationMidtransRequestPaymentService;
import com.codingtest.smarthome.models.midtrans.MidtransTrxRequestPayment;
import com.codingtest.smarthome.repositories.MidtransTrxRequestPaymentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PaymentRequestMidtransLinkLogic {

    private static final Logger LOG = LogManager.getLogger(PaymentRequestMidtransLinkLogic.class);

    @Autowired
    IntegrationMidtransRequestPaymentService integrationMidtransRequestPaymentService;

    @Autowired
    MidtransTrxRequestPaymentRepository midtransTrxRequestPaymentRepository;

    public IntegrationMidtransGetSnapResponse call(OrderPaymentRequestForm form) {
        LOG.info("start request payment from midtrans");

        IntegrationMidtransRequestPaymentForm integrationMidtransRequestPaymentForm = new IntegrationMidtransRequestPaymentForm()
                .setGross_amount(form.getGross_amount()).setFirst_name(form.getFirst_name()).setLast_name(form.getLast_name())
                .setEmail(form.getEmail()).setPhone(form.getPhone());
        IntegrationMidtransGetSnapResponse vtwebUrl = integrationMidtransRequestPaymentService.call(integrationMidtransRequestPaymentForm);

        MidtransTrxRequestPayment midtransTrxRequestPayment = new MidtransTrxRequestPayment()
                .setMidtrans_order_id(vtwebUrl.getOrder_id()).setRedirect_url(vtwebUrl.getRedirect_url())
                .setGross_amount(form.getGross_amount()).setStatus(0).setMidtrans_token_id(vtwebUrl.getToken());
        if (StringUtils.hasLength(form.getOrder_id())){
            midtransTrxRequestPayment.setOrder_id(form.getOrder_id());
        }
        midtransTrxRequestPaymentRepository.save(midtransTrxRequestPayment);

        LOG.info("finish request payment from midtrans");
        return vtwebUrl;
    }
}
