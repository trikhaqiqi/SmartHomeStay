package com.codingtest.smarthome.controllers.v1;

import com.codingtest.smarthome.dto.forms.OrderAddForm;
import com.codingtest.smarthome.dto.models.TrxOrderDto;
import com.codingtest.smarthome.dto.queryfilters.OrderQueryFilter;
import com.codingtest.smarthome.dto.responses.DatatableResponse;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.TrxOrder;
import com.codingtest.smarthome.services.order.add.OrderService;
import com.codingtest.smarthome.services.order.find.OrderFindService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/order")
public class OrderApi {

    private static final Logger LOG = LogManager.getLogger(OrderApi.class);

    @Autowired
    OrderService orderService;

    @Autowired
    OrderFindService orderFindService;

    @PostMapping
    @ApiOperation(value = "API add order", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    ResponseEntity add(@RequestBody @Valid OrderAddForm form, BindingResult br){
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            TrxOrder order = orderService.add(form);
            TrxOrderDto orderDto = order.toDto();
            return Response.ok(orderDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @GetMapping
    @ApiOperation(value = "API get additional facility", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public DatatableResponse find(OrderQueryFilter qf) {
        try {
            Page<TrxOrderDto> page = orderFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        }
        catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }
}
