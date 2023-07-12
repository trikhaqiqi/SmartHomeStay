package com.codingtest.smarthome.services.master_data.additional_facility.delete;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstAdditionalFacility;
import com.codingtest.smarthome.repositories.MstAdditionalFacilityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataAdditionalFacilityDeleteServiceImpl implements MasterDataAdditionalFacilityDeleteService {

    private static final Logger LOG = LogManager.getLogger(MasterDataAdditionalFacilityDeleteServiceImpl.class);

    @Autowired
    MstAdditionalFacilityRepository mstAdditionalFacilityRepository;

    @Override
    public MstAdditionalFacility call(String id) {
        LOG.info("start delete additional facility");

        MstAdditionalFacility mstAdditionalFacility = mstAdditionalFacilityRepository.findById(id).orElse(null);
        if (mstAdditionalFacility == null) {
            LOG.error("Additional facility dengan id " + id + " tidak ditemukan!");
            throw new BusinessException("Additional facilitytidak ditemukan!");
        }

        mstAdditionalFacility.setDeleted(DeletedStatusCode.ACTIVE.val());
        mstAdditionalFacilityRepository.save(mstAdditionalFacility);

        LOG.info("finish delete additional facility");
        return mstAdditionalFacility;
    }

}
