package com.daily.service;

import com.daily.dao.auto.CostMapper;
import com.daily.dao.my.CostDao;
import com.daily.dto.CostDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.Cost;
import com.daily.search.CostSearch;
import com.daily.tool.DateUtil;
import com.daily.tool.NullUtil;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
     *
     * @param search
     * @return
     */
    public List<CostDTO> sumClassify4CostList(CostSearch search) {
        return costDao.sumClassify4Cost(search);
    }

    /**
     * 统计周消费.
     *
     * @param search
     * @return
     */
    public List<CostDTO> sumWeekCost(CostSearch search) {
        List<CostDTO> zlist = costDao.sumWeekCost(search);
        if (NullUtil.listIsNull(zlist)) {
            return zlist;
        }
//        int count = zlist.size();
//        int maxSort = zlist.get(count - 2).getSort();
//        for (int j = 0; j < maxSort; j++) {
//            CostDTO info =  zlist.get(j);
//            int sort = info.getSort();
//            //排序位和下标位不同的，差几个补几个
//            //排序1开始，下标0开始
//            if (sort != j + 1) {
//                int add = 0;
//                for (int i = j + 1; i < sort; i++) {
//                    CostDTO temp = new CostDTO();
//                    temp.setSumMoney("0");
//                    temp.setSort(i);
//                    //获取前几周日期
//                    Date newWeek =DateUtil.getDayWeekDay(DateUtil.getDateFromStr(info.getTimeStr(),"yyyy-MM-dd"),i-sort);
//                    temp.setTimeStr(DateUtil.getDateStr2Date(newWeek,"yyyy-MM-dd"));
//                    add++;
//                    //向前缺的空位指定位置插入对象
//                    zlist.add(i-1,temp);
//                }
//                //补了之后，下标后移几位
//                j = j + add;
//            }
//        }
        return zlist;
    }

    /**
     * 保存消费记录.
     *
     * @param info 日记
     * @return 返回结果
     */
    public ResultBody saveCost(CostDTO info) {
        int ok = 0;
        if (info.getCostId() == null) {
            info.setCreated(new Date());
            info.setUpdated(new Date());
            ok = costMapper.insert(info);
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        } else {
            info.setUpdated(new Date());
            ok = costMapper.updateByPrimaryKeySelective(info);
            labelService.delete(info.getCostId(), 2, info.getUserId());
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getTag())) {
                labelService.batchSave(info.moodLabels());
            }
        }
        return Tools.getSaveResultBody(ok);
    }

    /**
     * 消费列表.
     *
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
     *
     * @param search
     * @return
     */
    public ResultBody sumClassify4Cost(CostSearch search) {
        List<CostDTO> list = null;
        switch (search.getTimeType()) {
            case 1:
                list = costDao.sumClassify4Cost(search);
                break;
            case 2:
                list = costDao.sumClassify4Cost(search);
                break;
            case 3:
                list = sumWeekCost(search);
                break;
            default:
                return new ResultBody(ResultCodeMsg.PARAM_ERR.getCode(), ResultCodeMsg.PARAM_ERR.getMsg());
        }
        ResultBody resultBody = new ResultBody();
        resultBody.setData(list);
        return resultBody;
    }

    public ResultBody getCost(Long cid) {
        Cost cost = costMapper.selectByPrimaryKey(cid);
        return new ResultBody(cost);
    }
}
