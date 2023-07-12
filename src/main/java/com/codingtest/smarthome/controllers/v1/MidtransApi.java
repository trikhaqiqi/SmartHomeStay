package com.codingtest.smarthome.controllers.v1;

import com.codingtest.smarthome.dto.forms.MidtransLogTransactionForm;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.services.midtrans.log_transaction.MidtransLogTransactionAddService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/midtrans")
@Api(value = "API Midtrans", description = "API Related to midtrans")
public class MidtransApi {

    private static final Logger LOG = LogManager.getLogger(MidtransApi.class);

    @Autowired
    MidtransLogTransactionAddService midtransLogTransactionAddService;

    @PostMapping("/log-transaction")
    @ApiOperation(value = "API add log transaction midtrans", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity add(@RequestBody @Valid MidtransLogTransactionForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            midtransLogTransactionAddService.call(form);
            return Response.ok(200).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
        catch (Exception e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

}
