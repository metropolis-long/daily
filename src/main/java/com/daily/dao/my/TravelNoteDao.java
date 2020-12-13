package com.daily.dao.my;


import com.daily.dto.TravelDTO;
import com.daily.search.LearnSearch;
import com.daily.search.TravelSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelNoteDao {
    /**
     * search learn record list.
     * and also push a record id to find one
     * @param search
     * @return
     */
    List<TravelDTO> findTravel(TravelSearch search);

    /**
     * count learn record.
     * @param search
     * @return
     */
    int count4FindTravel(TravelSearch search);
}
