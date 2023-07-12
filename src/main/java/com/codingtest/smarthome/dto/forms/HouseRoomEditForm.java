package com.codingtest.smarthome.dto.forms;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class HouseRoomEditForm extends BaseForm {

    @NotBlank(message = "ID harus diisi!")
    private String id;

    @NotBlank(message = "Nama harus diisi!")
    private String name;

    @NotBlank(message = "Deskripsi harus diisi!")
    private String description;

    @NotNull(message = "Kapasitas harus diisi!")
    private Integer capacity;

    @NotNull(message = "Harga hapus diisi!")
    private BigInteger price;
}
