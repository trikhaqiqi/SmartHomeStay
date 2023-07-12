package com.codingtest.smarthome.controllers.v1;

import com.codingtest.smarthome.dto.forms.HouseRoomAddForm;
import com.codingtest.smarthome.dto.forms.HouseRoomEditForm;
import com.codingtest.smarthome.dto.mappers.HouseRoomMapper;
import com.codingtest.smarthome.dto.models.MstHouseRoomDto;
import com.codingtest.smarthome.dto.queryfilters.HouseRoomQueryFilter;
import com.codingtest.smarthome.dto.responses.DatatableResponse;
import com.codingtest.smarthome.dto.responses.Response;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstHouseRoom;
import com.codingtest.smarthome.services.master_data.house_room.add.MasterDataHouseRoomAddService;
import com.codingtest.smarthome.services.master_data.house_room.delete.MasterDataHouseRoomDeleteService;
import com.codingtest.smarthome.services.master_data.house_room.edit.MasterDataHouseRoomEditService;
import com.codingtest.smarthome.services.master_data.house_room.find.MasterDataHouseRoomFindService;
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
@RequestMapping("/v1/house-room")
public class HouseRoomApi {

    private static final Logger LOG = LogManager.getLogger(HouseRoomApi.class);

    @Autowired private MasterDataHouseRoomAddService masterDataHouseRoomAddService;

    @Autowired private MasterDataHouseRoomDeleteService masterDataHouseRoomDeleteService;

    @Autowired private MasterDataHouseRoomEditService masterDataHouseRoomEditService;

    @Autowired private MasterDataHouseRoomFindService masterDataHouseRoomFindService;

    @PostMapping
    @ApiOperation(value = "API add house room", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity add(@RequestBody @Valid HouseRoomAddForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }
        try {
            MstHouseRoom houseRoom = masterDataHouseRoomAddService.call(form);
            MstHouseRoomDto houseRoomDto = HouseRoomMapper.toDto(houseRoom);
            return Response.ok(houseRoomDto).build();
        } catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }

    @DeleteMapping
    @ApiOperation(value = "API delete house room", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity delete(@RequestParam String id){
        if(!StringUtils.hasLength(id)){
            return Response.error("ID harus diisi!").build();
        }

        try {
            MstHouseRoom houseRoom = masterDataHouseRoomDeleteService.call(id);
            MstHouseRoomDto houseRoomDto = HouseRoomMapper.toDto(houseRoom);
            return Response.ok(houseRoomDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            return Response.error(e.getMessage(), id).build();
        }
        catch (Exception ex){
            LOG.error(ex.getMessage());
            return Response.error(ex.getMessage(), id).build();
        }
    }

    @PutMapping
    @ApiOperation(value = "API edit house room", response = Response.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiImplicitParam(name = "x-auth-token", value = "token login", required = true, allowEmptyValue = false, paramType = "header", example = "3122312ko3p1k23123ok123")
    public ResponseEntity edit(@RequestBody @Valid HouseRoomEditForm form, BindingResult br) {
        if(br.hasErrors()){
            String field = br.getFieldError().getField();
            String msg = br.getFieldErrors().get(0).getDefaultMessage();
            LOG.error(field + ": " + msg);
            return Response.error(msg).build();
        }

        try {
            MstHouseRoom mstHouseRoom = masterDataHouseRoomEditService.call(form);
            MstHouseRoomDto mstHouseRoomDto = HouseRoomMapper.toDto(mstHouseRoom);
            return Response.ok(mstHouseRoomDto).build();
        }
        catch (BusinessException e){
            LOG.error(e.getMessage());
            LOG.error(e.getClass().getCanonicalName());
            return Response.error(e.getMessage(), form).build();
        }
    }


    @GetMapping
    @ApiOperation(value = "API get house room", response = DatatableResponse.class)
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public DatatableResponse find(HouseRoomQueryFilter qf) {
        try {
            Page<MstHouseRoomDto> page = masterDataHouseRoomFindService.call(qf);
            return new DatatableResponse().setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception ex){
            LOG.error(ex.getMessage());
            return new DatatableResponse();
        }
    }

}
