package com.codingtest.smarthome.services.master_data.house_room.add;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.forms.HouseRoomAddForm;
import com.codingtest.smarthome.models.MstHouseRoom;
import com.codingtest.smarthome.repositories.MstHouseRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataHouseRoomAddServiceImpl implements MasterDataHouseRoomAddService{

    private static final Logger LOG = LogManager.getLogger(MasterDataHouseRoomAddServiceImpl.class);

    @Autowired
    MstHouseRoomRepository mstHouseRoomRepository;

    @Override
    public MstHouseRoom call(HouseRoomAddForm form) {

        LOG.info("start add House Room");

        MstHouseRoom houseRoom = new MstHouseRoom()
                .setName(form.getName()).setDescription(form.getDescription())
                        .setCapacity(form.getCapacity()).setPrice(form.getPrice())
                        .setDeleted(DeletedStatusCode.NON_ACTIVE.val());

        mstHouseRoomRepository.save(houseRoom);

        LOG.info("finish add House Room");
        return houseRoom;
    }

}
