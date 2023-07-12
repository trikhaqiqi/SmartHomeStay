package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public class AuthenticationLoginWithHandphoneForm extends BaseForm {

    @Pattern(regexp="^[0-9]+$", message = "Nomor handphone hanya boleh angka atau dikosongkan|Mobile numbers can only be numbers or leave blank")
    private String phone;

}
