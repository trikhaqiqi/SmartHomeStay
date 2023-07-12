package com.codingtest.smarthome.services.master_data.house_room.edit;

import com.codingtest.smarthome.dto.forms.HouseRoomEditForm;
import com.codingtest.smarthome.models.MstHouseRoom;

public interface MasterDataHouseRoomEditService {

    MstHouseRoom call(HouseRoomEditForm form);

}
