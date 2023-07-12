package com.codingtest.smarthome.services.master_data.house_room.add;

import com.codingtest.smarthome.dto.forms.HouseRoomAddForm;
import com.codingtest.smarthome.models.MstHouseRoom;

public interface MasterDataHouseRoomAddService {

    MstHouseRoom call(HouseRoomAddForm form);

}
