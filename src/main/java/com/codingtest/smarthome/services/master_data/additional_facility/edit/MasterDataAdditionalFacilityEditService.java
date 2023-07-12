package com.codingtest.smarthome.services.master_data.additional_facility.edit;

import com.codingtest.smarthome.dto.forms.AdditionalFacilityEditForm;
import com.codingtest.smarthome.models.MstAdditionalFacility;

public interface MasterDataAdditionalFacilityEditService {

    MstAdditionalFacility call(AdditionalFacilityEditForm form);

}
