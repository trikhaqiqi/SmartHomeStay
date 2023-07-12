package com.codingtest.smarthome.services.order.add.logic;

import com.codingtest.smarthome.dto.forms.OrderAddForm;
import com.codingtest.smarthome.dto.forms.OrderItemAddForm;
import com.codingtest.smarthome.dto.forms.OrderItemAdditionalFacilityAddForm;
import com.codingtest.smarthome.dto.forms.OrderPaymentRequestForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.integrations.midtrans.request_payment.responses.IntegrationMidtransGetSnapResponse;
import com.codingtest.smarthome.models.*;
import com.codingtest.smarthome.repositories.*;
import com.codingtest.smarthome.services.payment.PaymentService;
import com.codingtest.smarthome.utils.DateConverterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
public class OrderAddLogic {

    private static final Logger LOG = LogManager.getLogger(OrderAddLogic.class);

    @Autowired
    TrxOrderRepository trxOrderRepository;

    @Autowired
    TrxOrderCustomerInfoRepository trxOrderCustomerInfoRepository;

    @Autowired
    TrxOrderItemRepository trxOrderItemRepository;

    @Autowired
    TrxOrderAdditionalItemRepository trxOrderAdditionalItemRepository;

    @Autowired
    MstHouseRoomRepository mstHouseRoomRepository;

    @Autowired
    MstAdditionalFacilityRepository mstAdditionalFacilityRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    PaymentService paymentService;

    @Transactional
    public TrxOrder call(OrderAddForm form) {
        LOG.info("start add order");

        SysUser user = sysUserRepository.findById(form.getUser_id()).orElse(null);
        if (user == null) {
            LOG.error("Paket tidak ditemukan!");
            throw new BusinessException("Paket tidak ditemukan!");
        }

        BigInteger total =  new BigInteger("0");
        TrxOrder order = new TrxOrder()
                .setUser_id(form.getUser_id())
                .setDate(DateConverterUtil.getTodayDate()).setIsPaid(false)
                .setUniqueTransactionId(generateUniqueTransactionId());
        trxOrderRepository.save(order);

        // order house room
        if (form.getOrderItems() != null && form.getOrderItems().size() > 0) {
            for (OrderItemAddForm orderItemAddForm: form.getOrderItems()) {
                // ambil room house
                MstHouseRoom addOnHouseRoom = mstHouseRoomRepository.findById(orderItemAddForm.getHouse_room_id()).orElse(null);
                if (addOnHouseRoom == null) {
                    LOG.error("House Room tidak ditemukan!");
                    throw new BusinessException("House Room tidak ditemukan!");
                }

                BigInteger totalPrice = addOnHouseRoom.getPrice().multiply(new BigInteger("" + orderItemAddForm.getQuantity()))
                                            .multiply(new BigInteger("" + orderItemAddForm.getDay()));

                TrxOrderItem newOrderItem = new TrxOrderItem()
                        .setOrderId(order.getId()).setQuantity(orderItemAddForm.getQuantity()).setDay(orderItemAddForm.getDay()).setHouse_room_id(addOnHouseRoom.getId()).setOrigin_price(addOnHouseRoom.getPrice())
                        .setPrice(addOnHouseRoom.getPrice()).setTotal_price(totalPrice);
                trxOrderItemRepository.save(newOrderItem);

                TrxOrderCustomerInfo orderCustomerInfo = new TrxOrderCustomerInfo()
                        .setUser_id(form.getUser_id())
                        .setOrderId(order.getId())
                        .setCheck_in(orderItemAddForm.getCheck_in())
                        .setCheck_out(orderItemAddForm.getCheck_out());
                trxOrderCustomerInfoRepository.save(orderCustomerInfo);

                total = total.add(totalPrice);
            }
        }

        // order additional facilities
        List<OrderItemAdditionalFacilityAddForm> orderAdditionalFacilities = form.getOrderAdditionalFacilities();
        if (orderAdditionalFacilities != null && !orderAdditionalFacilities.isEmpty()) {
            for (OrderItemAdditionalFacilityAddForm orderItemAdditionalFacilityAddForm : orderAdditionalFacilities) {
                MstAdditionalFacility additionalFacility = mstAdditionalFacilityRepository.findById(orderItemAdditionalFacilityAddForm.getAdditional_facility_id()).orElse(null);
                if (additionalFacility == null) {
                    LOG.error("AdditionalFacility tidak ditemukan!");
                    throw new BusinessException("AdditionalFacility tidak ditemukan!");
                }

                BigInteger totalPrice = additionalFacility.getPrice().multiply(new BigInteger("" + orderItemAdditionalFacilityAddForm.getQuantity()));
                TrxOrderAdditionalItem newOrderAdditionalItem = new TrxOrderAdditionalItem()
                        .setOrderId(order.getId()).setQuantity(orderItemAdditionalFacilityAddForm.getQuantity()).setAdditional_facility_id(additionalFacility.getId()).setOrigin_price(additionalFacility.getPrice())
                        .setPrice(additionalFacility.getPrice()).setTotal_price(totalPrice);
                trxOrderAdditionalItemRepository.save(newOrderAdditionalItem);
                total = total.add(totalPrice);
            }
        }

        order.setTotal(total);
        trxOrderRepository.save(order);

        // melakukan permintaan link snap midtrans
        if (total.compareTo(new BigInteger("0")) > 0) {
            OrderPaymentRequestForm orderPaymentRequestForm = new OrderPaymentRequestForm()
                    .setGross_amount(order.getTotal()).setOrder_id(order.getId())
                    .setFirst_name("").setLast_name("");
//                    .setPhone(form.getOrderCustomerInfo().getPhone())
//                    .setEmail(form.getOrderCustomerInfo().getEmail())

//            orderPaymentRequestForm
//                    .setFirst_name(form.getOrderCustomerInfo().getFirstname())
//                    .setLast_name(form.getOrderCustomerInfo().getLastname());
            try {
                IntegrationMidtransGetSnapResponse integrationMidtransGetSnapResponse = paymentService.requestMidtransLink(orderPaymentRequestForm);
                if (integrationMidtransGetSnapResponse == null){
                    throw new BusinessException("Gagal melanjutkan transaksi, karena gagal menampilkan pembayaran!");
                }

                order.setRedirectPaymentUrl(integrationMidtransGetSnapResponse.getRedirect_url());
            } catch (Exception e){
                throw new BusinessException("Gagal melanjutkan transaksi, karena gagal menampilkan pembayaran!");
            }
        }

        LOG.info("finish add order");
        return order;
    }

    private String generateUniqueTransactionId() {
        return "TRX" + DateConverterUtil.currentTimeMilis();
    }

}
