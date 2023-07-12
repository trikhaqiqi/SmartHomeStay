package com.codingtest.smarthome.models;

import com.codingtest.smarthome.models.hooks.BaseHook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "sys_roles")
@EntityListeners(BaseHook.class)
public class SysRole extends BaseModel implements Serializable {

    private String name;

    private String code;

    private Boolean deleted;

}
