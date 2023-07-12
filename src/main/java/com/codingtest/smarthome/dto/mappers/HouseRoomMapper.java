package com.codingtest.smarthome.dto.mappers;

import com.codingtest.smarthome.dto.models.MstHouseRoomDto;
import com.codingtest.smarthome.models.MstHouseRoom;

public class HouseRoomMapper {

    public static MstHouseRoomDto toDto(MstHouseRoom houseRoom) {
        MstHouseRoomDto mstHouseRoomDto = new MstHouseRoomDto();
        mstHouseRoomDto.setName(houseRoom.getName())
                .setDescription(houseRoom.getDescription())
                .setCapacity(houseRoom.getCapacity())
                .setPrice(houseRoom.getPrice())
                .setDeleted(houseRoom.getDeleted())
                .setId(houseRoom.getId())
                .setCreated_at(houseRoom.getCreated_at())
                .setUpdated_at(houseRoom.getUpdated_at());

        return mstHouseRoomDto;
    }

}
