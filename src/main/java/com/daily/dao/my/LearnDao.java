package com.daily.dao.my;

import com.daily.dto.LearnDTO;
import com.daily.search.LearnSearch;
import com.daily.search.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LearnDao {
    /**
     * search learn record list.
     * and also push a record id to find one
     * @param search
     * @return
     */
    List<LearnDTO> findLearn(LearnSearch search);

    /**
     * count learn record.
     * @param search
     * @return
     */
    int count4FindLearn(LearnSearch search);
}
