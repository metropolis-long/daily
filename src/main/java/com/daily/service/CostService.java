package com.daily.service;

import com.daily.dao.auto.CostMapper;
import com.daily.dao.auto.LabelMapper;
import com.daily.dao.my.CostDao;
import com.daily.dto.CostDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.Label;
import com.daily.search.CostSearch;
import com.daily.tool.NullUtil;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**

 */
/**
 * @ClassName CostService
 * @Description TODO
 * 消费业务.
 * 业务层不在使用接口规范
 * @Author metropolis-long
 * @Date 2020/10/18 19:27
 * @VERSION 1.0.0
 */
@Service(value = "costService")
public class CostService {
    @Autowired
    private CostDao costDao;
    @Autowired
    private CostMapper costMapper;

    @Autowired
    private LabelService labelService;
    /**
     * 统计消费.
     * @param search
     * @return
     */
    public List<CostDTO> sumClassify4CostList(CostSearch search){
        return costDao.sumClassify4Cost(search);
    }
    /**
     * 统计周消费.
     * @param search
     * @return
     */
    public List<CostDTO> sumWeekCost(CostSearch search){
        return costDao.sumWeekCost(search);
    }
    /**
     * 保存消费记录.
     * @param info 日记
     * @return 返回结果
     */
    public ResultBody saveCost(CostDTO info) {
        int ok =0;
        if (info.getCostId()==null){
            info.setCreated(new Date());
            info.setUpdated(new Date());
            ok = costMapper.insert(info);
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        }else{
            info.setUpdated(new Date());
            costMapper.updateByPrimaryKeySelective(info);
            labelService.delete(info.getCostId(),2,info.getUserId());
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        }
        return Tools.getSaveResultBody(ok);
    }

    /**
     * 消费列表.
     * @param search
     * @return
     */
    public ResultBody<List<CostDTO>> findMyCost(CostSearch search) {
        List<CostDTO> list = costDao.findMyCost(search);
        ResultBody resultBody = new ResultBody();
        resultBody.setData(list);
        resultBody.setCount(costDao.count4FindMyCost(search));
        return resultBody;
    }
    /**
     * 消费统计列表.
     * @param search
     * @return
     */
    public ResultBody sumClassify4Cost(CostSearch search) {
        List<CostDTO> list =null;
        switch (search.getTimeType()){
            case 1:
                list=costDao.sumClassify4Cost(search);
                break;
            case 2:
                list=costDao.sumClassify4Cost(search);
                break;
            case 3:
                list=costDao.sumWeekCost(search);
                break;
            default:
                return new ResultBody(ResultCodeMsg.PARAM_ERR.getCode(),ResultCodeMsg.PARAM_ERR.getMsg());
        }
        ResultBody resultBody = new ResultBody();
        resultBody.setData(list);
        return resultBody;
    }
}
