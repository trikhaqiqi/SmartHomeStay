package com.codingtest.smarthome.repositories;

import com.codingtest.smarthome.models.MstHouseRoom;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MstHouseRoomRepository extends PagingAndSortingRepository<MstHouseRoom, String>, QuerydslPredicateExecutor<MstHouseRoom> {
}
