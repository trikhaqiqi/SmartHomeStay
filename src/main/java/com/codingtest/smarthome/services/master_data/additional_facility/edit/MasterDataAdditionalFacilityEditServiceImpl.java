package com.codingtest.smarthome.services.master_data.additional_facility.edit;

import com.codingtest.smarthome.dto.forms.AdditionalFacilityEditForm;
import com.codingtest.smarthome.exceptions.BusinessException;
import com.codingtest.smarthome.models.MstAdditionalFacility;
import com.codingtest.smarthome.repositories.MstAdditionalFacilityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataAdditionalFacilityEditServiceImpl implements MasterDataAdditionalFacilityEditService {

    private static final Logger LOG = LogManager.getLogger(MasterDataAdditionalFacilityEditServiceImpl.class);

    @Autowired
    MstAdditionalFacilityRepository mstAdditionalFacilityRepository;

    @Override
    public MstAdditionalFacility call(AdditionalFacilityEditForm form) {
        LOG.info("start edit additional facility");

        MstAdditionalFacility additionalFacility = mstAdditionalFacilityRepository.findById(form.getId()).orElse(null);
        if (additionalFacility == null) {
            LOG.error("Additional facility dengan id " + form.getId() + " tidak ditemukan!");
            throw new BusinessException("Additional facility tidak ditemukan!");
        }

        additionalFacility.setName(form.getName()).setDescription(form.getDescription()).setPrice(form.getPrice());

        mstAdditionalFacilityRepository.save(additionalFacility);

        LOG.info("finish edit additional facility");
        return additionalFacility;
    }

}
