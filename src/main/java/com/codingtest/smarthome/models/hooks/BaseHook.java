package com.codingtest.smarthome.models.hooks;

import com.codingtest.smarthome.models.BaseModel;
import com.codingtest.smarthome.utils.DateConverterUtil;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class BaseHook {

    @PrePersist
    public void methodExecuteBeforeSave(final BaseModel model){
        model.setCreated_at(DateConverterUtil.currentTimeMilis());
        model.setUpdated_at(DateConverterUtil.currentTimeMilis());
    }

    @PreUpdate
    public void methodExecuteBeforeUpdate(final BaseModel model){
        model.setUpdated_at(DateConverterUtil.currentTimeMilis());
    }

}
