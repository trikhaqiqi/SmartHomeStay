package com.codingtest.smarthome.services.master_data.house_room.edit;

import com.codingtest.smarthome.dto.forms.HouseRoomEditForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstHouseRoom;
import com.codingtest.smarthome.repositories.MstHouseRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataHouseRoomEditServiceImpl implements MasterDataHouseRoomEditService {


    private static final Logger LOG = LogManager.getLogger(MasterDataHouseRoomEditServiceImpl.class);

    @Autowired
    MstHouseRoomRepository mstHouseRoomRepository;

    @Override
    public MstHouseRoom call(HouseRoomEditForm form) {
        LOG.info("start edit house room");

        MstHouseRoom houseRoom = mstHouseRoomRepository.findById(form.getId()).orElse(null);
        if (houseRoom == null) {
            LOG.error("House room dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("House room tidak ditemukan!");
        }

        houseRoom.setName(form.getName()).setDescription(form.getDescription()).setCapacity(form.getCapacity())
                        .setPrice(form.getPrice());

        mstHouseRoomRepository.save(houseRoom);

        LOG.info("finish edit house room");
        return houseRoom;
    }
}
