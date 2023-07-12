package com.codingtest.smarthome.services.master_data.house_room.find;

import com.codingtest.smarthome.dto.enums.DeletedStatusCode;
import com.codingtest.smarthome.dto.mappers.HouseRoomMapper;
import com.codingtest.smarthome.dto.models.MstHouseRoomDto;
import com.codingtest.smarthome.dto.queryfilters.HouseRoomQueryFilter;
import com.codingtest.smarthome.models.MstHouseRoom;
import com.codingtest.smarthome.models.QMstHouseRoom;
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
public class MasterDataHouseRoomFindServiceImpl implements MasterDataHouseRoomFindService {

    private static final Logger LOG = LogManager.getLogger(MasterDataHouseRoomFindServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<MstHouseRoomDto> call(HouseRoomQueryFilter qf) {
        LOG.info("start find House Room");

        List<MstHouseRoomDto> dtoList = new ArrayList<>();

        QMstHouseRoom qMstHouseRoom = QMstHouseRoom.mstHouseRoom;

        JPAQuery<MstHouseRoom> query = new JPAQuery<MstHouseRoom>(entityManager).from(qMstHouseRoom)
                        .limit(qf.getLength()).offset(qf.getStart()).where(qMstHouseRoom.deleted.eq(DeletedStatusCode.NON_ACTIVE.val()));

        if (StringUtils.hasLength(qf.getId())) {
            query.where(qMstHouseRoom.id.eq(qf.getId()));
        }

        if (StringUtils.hasLength(qf.getSearch_text())) {
            query.where(qMstHouseRoom.name.containsIgnoreCase(qf.getSearch_text())
                            .or(qMstHouseRoom.description.containsIgnoreCase(qf.getSearch_text()))
            );
        }

        QueryResults<MstHouseRoom> results = query.fetchResults();

        dtoList = results.getResults().stream()
                .map(HouseRoomMapper::toDto)
                .collect(Collectors.toList());


        LOG.info("start find House Room");
        return new CustomPageImpl<>(dtoList, qf.pageable(), results.getTotal());
    }

}
