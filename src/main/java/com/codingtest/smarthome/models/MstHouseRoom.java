package com.codingtest.smarthome.models;

import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "mst_house_rooms")
@EntityListeners(BaseHook.class)
public class MstHouseRoom extends BaseModel{

    private String name;

    private String description;

    private BigInteger price;

    private Boolean deleted;

    private Integer capacity;

}
