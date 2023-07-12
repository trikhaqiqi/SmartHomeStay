package com.codingtest.smarthome.controllers.v1;

import com.codingtest.smarthome.dto.forms.AdditionalFacilityAddForm;
import com.codingtest.smarthome.dto.forms.AdditionalFacilityEditForm;
import com.codingtest.smarthome.dto.mappers.AdditionalFacilityMapper;
import com.codingtest.smarthome.dto.models.MstAdditionalFacilityDto;
import com.codingtest.smarthome.dto.queryfilters.AdditionalFacilityQueryFilter;
import com.codingtest.smarthome.dto.responses.DatatableResponse;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstAdditionalFacility;
import com.codingtest.smarthome.services.master_data.additional_facility.add.MasterDataAdditionalFacilityAddService;
import com.codingtest.smarthome.services.master_data.additional_facility.delete.MasterDataAdditionalFacilityDeleteService;
import com.codingtest.smarthome.services.master_data.additional_facility.edit.MasterDataAdditionalFacilityEditService;
import com.codingtest.smarthome.services.master_data.additional_facility.find.MasterDataAdditionalFacilityFindService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/additional-facility")
public class AdditionalFacilityApi {

    private static final Logger LOG = LogManager.getLogger(AdditionalFacilityApi.class);

    @Autowired private MasterDataAdditionalFacilityAddService masterDataAdditionalFacilityAddService;

    @Autowired private MasterDataAdditionalFacilityDeleteService masterDataAdditionalFacilityDeleteService;

    @Autowired private MasterDataAdditionalFacilityEditService masterDataAdditionalFacilityEditService;

    @Autowired private MasterDataAdditionalFacilityFindService masterDataAdditionalFacilityFindService;

    @PostMapping
    @ApiOperation(value = "API add additional facility", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid AdditionalFacilityAddForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }
        try {
            MstAdditionalFacility additionalFacility = masterDataAdditionalFacilityAddService.call(form);
            MstAdditionalFacilityDto additionalFacilityDto = AdditionalFacilityMapper.toDto(additionalFacility);
            return Response.ok(additionalFacilityDto).build();
        } catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete additional facility", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity delete(@RequestParam String id) {
        if (!StringUtils.hasLength(id)) {
            return Response.error("ID harus diisi!").build();
        }

        try {
            MstAdditionalFacility additionalFacility = masterDataAdditionalFacilityDeleteService.call(id);
            MstAdditionalFacilityDto additionalFacilityDto = AdditionalFacilityMapper.toDto(additionalFacility);
            return Response.ok(additionalFacilityDto).build();
        } catch (BusinessException e) {
            LOG.error(e.getMessage());
            return Response.error(e.getMessage(), id).build();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage(), id).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit additional facility", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid AdditionalFacilityEditForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstAdditionalFacility additionalFacility = masterDataAdditionalFacilityEditService.call(form);
            MstAdditionalFacilityDto additionalFacilityDto = AdditionalFacilityMapper.toDto(additionalFacility);
            return Response.ok(additionalFacilityDto).build();
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
    public DatatableResponse find(AdditionalFacilityQueryFilter qf) {
        try {
            Page<MstAdditionalFacilityDto> page = masterDataAdditionalFacilityFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        }
        catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }
}
