package com.daily.dao.my;

import com.daily.dto.CostDTO;
import com.daily.search.CostSearch;

import java.util.List;

public interface CostDao {
    /**
     * 统计月消费或者年消费.
     * @param search
     * @return
     */
    List<CostDTO> sumClassify4Cost(CostSearch search);

    /**
     * 统计周消费.
     * @param search
     * @return
     */
    List<CostDTO> sumWeekCost(CostSearch search);
    List<CostDTO> findMyCost(CostSearch search);

    int count4FindMyCost(CostSearch search);

}
