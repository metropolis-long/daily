package com.daily.service;

import com.daily.dto.DiaryDTO;
import com.daily.msg.ResultBody;
import com.daily.search.DailySearch;

import java.util.List;

public interface IDiaryService {
    /**
     * 查询个人日记
     * @param search
     * @return
     */
    List<DiaryDTO> findDiaryList(DailySearch search);
    ResultBody<List<DiaryDTO>> findAllDiary(DailySearch search);

    ResultBody saveDiary(DiaryDTO info);

    ResultBody detail(DailySearch search);
}
