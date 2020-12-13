package com.daily.service;

import com.daily.dao.auto.LearnMapper;
import com.daily.dao.my.LearnDao;
import com.daily.dto.LearnDTO;
import com.daily.msg.ResultBody;
import com.daily.pojo.LearnExample;
import com.daily.search.LearnSearch;
import com.daily.tool.NullUtil;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LearnService
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/26 23:17
 * @VERSION 1.0.0
 */
@Service(value = "learnService")
public class LearnService {

    @Autowired
    private LearnDao learnDao;
    @Autowired
    private LearnMapper learnMapper;
    @Autowired
    private LabelService labelService;

    /**
     * learn list .
     *
     * @param search
     * @return
     */
    public ResultBody<List<LearnDTO>> findMyLearn(LearnSearch search) {
        List<LearnDTO> list = learnDao.findLearn(search);
        ResultBody resultBody = new ResultBody();
        resultBody.setData(list);
        resultBody.setCount(learnDao.count4FindLearn(search));
        return resultBody;
    }

    /**
     * learn detail content.
     *
     * @param search push id in search
     * @return learn
     */
    public ResultBody detail(LearnSearch search) {
        List<LearnDTO> list = learnDao.findLearn(search);
        if (!NullUtil.listIsNull(list)) {
            return new ResultBody(list.get(0));
        }
        return new ResultBody();
    }

    public ResultBody save(LearnDTO info) {
        int ok = 0;
        if (info.getLearnId() == null) {
            info.setCreated(new Date());
            info.setUpdated(new Date());
            ok = learnMapper.insert(info);
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        } else {
            LearnExample params = new LearnExample();
            params.createCriteria().andLearnIdEqualTo(info.getLearnId());
            info.setUpdated(new Date());
            ok = learnMapper.updateByExampleSelective(info, params);
            labelService.delete(info.getLearnId(),3,info.getUserId());
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        }
        return Tools.getSaveResultBody(ok);
    }
}
