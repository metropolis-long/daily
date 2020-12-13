package com.daily.search;

import com.daily.search.SearchDTO;

/**
 * @ClassName LearnSearch
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/26 22:49
 * @VERSION 1.0.0
 */
public class LearnSearch extends SearchDTO {
    private Long learnId;

    public Long getLearnId() {
        return learnId;
    }

    public void setLearnId(Long learnId) {
        this.learnId = learnId;
    }
}
