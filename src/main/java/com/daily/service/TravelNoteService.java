package com.daily.service;

import com.daily.dao.auto.TravelNoteMapper;
import com.daily.dao.my.TravelNoteDao;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.dto.TravelDTO;
import com.daily.pojo.TravelNoteExample;
import com.daily.search.TravelSearch;
import com.daily.tool.NullUtil;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "travelNoteService")
public class TravelNoteService {

    @Autowired
    private TravelNoteDao travelNoteDao;

    @Autowired
    private TravelNoteMapper travelNoteMapper;
    @Autowired
    private LabelService labelService;

    public List<TravelDTO> findTravelNote(TravelSearch search) {
        return travelNoteDao.findTravel(search);
    }

    public ResultBody<List<TravelDTO>> findAllTravel(TravelSearch search) {
        ResultBody resultBody = new ResultBody();
        resultBody.setData(findTravelNote(search));
        resultBody.setCount(travelNoteDao.count4FindTravel(search));
        return resultBody;
    }

    /**
     * 保存日记.
     *
     * @param info 日记
     * @return 返回结果
     */
    public ResultBody saveTravelNote(TravelDTO info) {
        int ok = 0;
        if (info.getTravelId() == null) {
            info.setCreated(new Date());
            info.setUpdated(new Date());
            ok = travelNoteMapper.insert(info);
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        } else {
            TravelNoteExample params = new TravelNoteExample();
            params.createCriteria().andTravelIdEqualTo(info.getTravelId());
            info.setUpdated(new Date());
            ok = travelNoteMapper.updateByExampleSelective(info, params);
            labelService.delete(info.getTravelId(),4,info.getUserId());
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        }
        return Tools.getSaveResultBody(ok);
    }

    /**
     * 日记详情.
     * search里面传日记id
     *
     * @param search
     * @return
     */
    public ResultBody detail(TravelSearch search) {
        List list = findTravelNote(search);
        if (NullUtil.listIsNull(list))
            return new ResultBody();
        return new ResultBody(ResultCodeMsg.OK.getCode(), ResultCodeMsg.OK.getMsg(), list.get(0));
    }
}
