package com.codingtest.smarthome.services.master_data.house_room.find;

import com.codingtest.smarthome.dto.models.MstHouseRoomDto;
import com.codingtest.smarthome.dto.queryfilters.HouseRoomQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataHouseRoomFindService {

    Page<MstHouseRoomDto> call(HouseRoomQueryFilter qf);

}
