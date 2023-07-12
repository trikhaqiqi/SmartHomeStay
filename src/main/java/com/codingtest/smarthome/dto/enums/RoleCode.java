package com.codingtest.smarthome.dto.enums;

public enum RoleCode {

    CUSTOMER("customer"),
    EMPLOYEE("employee");

    private String val;

    RoleCode(String val){this.val = val;}

    public String val(){
        return this.val;
    }

    public String label(){
        if(CUSTOMER.val.equals(this.val)){
            return "Customer";
        } if(EMPLOYEE.val.equals(this.val)){
            return "Employee";
        }

        return null;
    }

    public static RoleCode val(String val){
        if(CUSTOMER.val.equals(val)) {
            return RoleCode.CUSTOMER;
        } if (EMPLOYEE.val.equals(val)) {
            return RoleCode.EMPLOYEE;
        }

        return null;
    }

}
