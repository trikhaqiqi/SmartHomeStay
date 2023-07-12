package com.codingtest.smarthome.dto.mappers;

import com.codingtest.smarthome.dto.models.MstAdditionalFacilityDto;
import com.codingtest.smarthome.models.MstAdditionalFacility;

public class AdditionalFacilityMapper {

    public static MstAdditionalFacilityDto toDto(MstAdditionalFacility additionalFacility) {
        MstAdditionalFacilityDto mstAdditionalFacilityDto = new MstAdditionalFacilityDto();
        mstAdditionalFacilityDto.setName(additionalFacility.getName())
                .setDescription(additionalFacility.getDescription())
                .setPrice(additionalFacility.getPrice())
                .setDeleted(additionalFacility.getDeleted())
                .setId(additionalFacility.getId())
                .setCreated_at(additionalFacility.getCreated_at())
                .setUpdated_at(additionalFacility.getUpdated_at());

        return mstAdditionalFacilityDto;
    }

}
