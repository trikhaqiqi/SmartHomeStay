package com.codingtest.smarthome.services.master_data.additional_facility.add;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.forms.AdditionalFacilityAddForm;
import com.codingtest.smarthome.models.MstAdditionalFacility;
import com.codingtest.smarthome.repositories.MstAdditionalFacilityRepository;
import com.codingtest.smarthome.services.master_data.house_room.add.MasterDataHouseRoomAddServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataAdditionalFacilityAddServiceImpl implements MasterDataAdditionalFacilityAddService {

    private static final Logger LOG = LogManager.getLogger(MasterDataHouseRoomAddServiceImpl.class);

    @Autowired
    MstAdditionalFacilityRepository mstAdditionalFacilityRepository;

    @Override
    public MstAdditionalFacility call(AdditionalFacilityAddForm form) {
        LOG.info("start add additional facilities");

        MstAdditionalFacility additionalFacility = new MstAdditionalFacility()
                .setName(form.getName()).setDescription(form.getDescription()).setPrice(form.getPrice())
                        .setDeleted(DeletedStatusCode.NON_ACTIVE.val());

        mstAdditionalFacilityRepository.save(additionalFacility);

        LOG.info("finish add additional facilities");
        return additionalFacility;
    }

}
