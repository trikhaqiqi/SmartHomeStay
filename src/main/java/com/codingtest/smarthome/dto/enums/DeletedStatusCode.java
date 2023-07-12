package com.codingtest.smarthome.dto.enums;

public enum DeletedStatusCode {

    ACTIVE(true), NON_ACTIVE(false);

    private Boolean val;

    DeletedStatusCode(Boolean val){this.val = val;}

    public Boolean val(){
        return this.val;
    }

}
