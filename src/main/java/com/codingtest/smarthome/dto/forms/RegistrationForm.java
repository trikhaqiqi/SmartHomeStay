package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Accessors(chain = true)
public class RegistrationForm extends BaseForm {

    @NotBlank(message = "Email harus diisi|Email is required")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Email harus di isi dengan format email yang benar|Email must be filled in the correct email format")
    private String email;

    @NotBlank(message = "Password harus diisi|Password is required")
    private String pwd;

    @NotBlank(message = "Nama harus diisi|Name is required")
    private String name;

    @Pattern(regexp="^[0-9]+$", message = "Nomor handphone hanya boleh angka atau dikosongkan|Mobile numbers can only be numbers or leave blank")
    private String phone;

}
