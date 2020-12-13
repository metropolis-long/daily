package com.daily.dao.my;

import com.daily.dto.DiaryDTO;
import com.daily.search.DailySearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryDao {
    /**
     * 查询日记，可传日记id获取单条日记详情
     * @param search
     * @return
     */
    List<DiaryDTO> findAllDiary(DailySearch search);
    int count4FindAllDiary(DailySearch search);
}
