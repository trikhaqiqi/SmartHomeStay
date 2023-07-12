package com.codingtest.smarthome.services.master_data.house_room.delete;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstHouseRoom;
import com.codingtest.smarthome.repositories.MstHouseRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataHouseRoomDeleteServiceImpl implements MasterDataHouseRoomDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataHouseRoomDeleteServiceImpl.class);

    @Autowired
    MstHouseRoomRepository mstHouseRoomRepository;

    @Override
    public MstHouseRoom call(String id) {
        LOG.info("start delete house room");

        MstHouseRoom mstHouseRoom = mstHouseRoomRepository.findById(id).orElse(null);
        if (mstHouseRoom == null) {
            LOG.error("House room dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("House room tidak ditemukan!");
        }

        mstHouseRoom.setDeleted(DeletedStatusCode.ACTIVE.val());
        mstHouseRoomRepository.save(mstHouseRoom);

        LOG.info("finish delete house room");
        return mstHouseRoom;
    }

}
