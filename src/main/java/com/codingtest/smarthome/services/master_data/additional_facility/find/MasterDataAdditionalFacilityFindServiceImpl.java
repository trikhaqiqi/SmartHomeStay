package com.codingtest.smarthome.services.master_data.additional_facility.find;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.mappers.AdditionalFacilityMapper;
import com.codingtest.smarthome.dto.models.MstAdditionalFacilityDto;
import com.codingtest.smarthome.dto.queryfilters.AdditionalFacilityQueryFilter;
import com.codingtest.smarthome.models.MstAdditionalFacility;
import com.codingtest.smarthome.models.QMstAdditionalFacility;
import com.codingtest.smarthome.utils.CustomPageImpl;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDataAdditionalFacilityFindServiceImpl implements MasterDataAdditionalFacilityFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataAdditionalFacilityFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<MstAdditionalFacilityDto> call(AdditionalFacilityQueryFilter qf) {

        LOG.info("start find Additional Facilities");

        List<MstAdditionalFacilityDto> dtoList = new ArrayList<>();

        QMstAdditionalFacility qMstAdditionalFacility = QMstAdditionalFacility.mstAdditionalFacility;

        JPAQuery<MstAdditionalFacility> query = new JPAQuery<MstAdditionalFacility>(entityManager).from(qMstAdditionalFacility)
                        .limit(qf.getLength()).offset(qf.getStart()).where(qMstAdditionalFacility.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qMstAdditionalFacility.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getSearch_text())) {
            query.where(qMstAdditionalFacility.name.containsIgnoreCase(qf.getSearch_text())
                    .or(qMstAdditionalFacility.description.containsIgnoreCase(qf.getSearch_text()))
            );
        }

        QueryResults<MstAdditionalFacility> results = query.fetchResults();

        dtoList = results.getResults().stream()
                        .map(AdditionalFacilityMapper::toDto)
                                .collect(Collectors.toList());

        LOG.info("finish find Additional Facilities");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }

}
