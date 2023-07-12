package com.codingtest.smarthome.models.midtrans;

import com.codingtest.smarthome.dto.forms.MidtransLogTransactionForm;
import com.codingtest.smarthome.models.BaseModel;
import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "midtrans_log_transactions")
@EntityListeners(BaseHook.class)
public class MidtransLogTransaction extends BaseModel {

	private String approval_code;
	
	private String bank;
	
	private String finish_redirect_url;
	
	private String fraud_status;
	
	private double gross_amount;
	
	private String masked_card;
	
	private String order_id;
	
	private String payment_type;
	
	private String status_code;
	
	private String status_message;
	
	private String transaction_id;
	
	private String transaction_status;
	
	private String transaction_time;

	public static MidtransLogTransaction convertFrom(MidtransLogTransactionForm form){
		MidtransLogTransaction midtransLogTransaction = new MidtransLogTransaction()
				.setApproval_code(form.getApproval_code()).setBank(form.getBank()).setFinish_redirect_url(form.getFinish_redirect_url())
				.setFraud_status(form.getFraud_status()).setGross_amount(form.getGross_amount()).setMasked_card(form.getMasked_card())
				.setOrder_id(form.getOrder_id()).setPayment_type(form.getPayment_type()).setStatus_code(form.getStatus_code())
				.setStatus_message(form.getStatus_message()).setTransaction_id(form.getTransaction_id()).setTransaction_status(form.getTransaction_status())
				.setTransaction_time(form.getTransaction_time());

		return midtransLogTransaction;
	}

}
