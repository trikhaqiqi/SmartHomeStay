package com.codingtest.smarthome.services.master_data.additional_facility.find;

import com.codingtest.smarthome.dto.models.MstAdditionalFacilityDto;
import com.codingtest.smarthome.dto.queryfilters.AdditionalFacilityQueryFilter;
import org.springframework.data.domain.Page;

public interface MasterDataAdditionalFacilityFindService {

    Page<MstAdditionalFacilityDto> call(AdditionalFacilityQueryFilter qf);

}
