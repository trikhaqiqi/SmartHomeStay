package com.codingtest.smarthome.services.master_data.additional_facility.add;

import com.codingtest.smarthome.dto.forms.AdditionalFacilityAddForm;
import com.codingtest.smarthome.models.MstAdditionalFacility;

public interface MasterDataAdditionalFacilityAddService {

    MstAdditionalFacility call(AdditionalFacilityAddForm form);

}
