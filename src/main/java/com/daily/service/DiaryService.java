package com.daily.service;

import com.daily.dao.auto.DiaryMapper;
import com.daily.dao.auto.LabelMapper;
import com.daily.dao.my.DiaryDao;
import com.daily.dto.DiaryDTO;
import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;
import com.daily.pojo.DiaryExample;
import com.daily.search.DailySearch;
import com.daily.tool.EncrypDES;
import com.daily.tool.NullUtil;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DiaryService implements IDiaryService {

    @Autowired
    private DiaryDao diaryDao;

    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private LabelService labelService;

    private EncrypDES encrypDES = new EncrypDES();
    @Override
    public List<DiaryDTO> findDiaryList(DailySearch search) {
        List<DiaryDTO> list = diaryDao.findAllDiary(search);
        list.forEach(info->{
            if (!NullUtil.isNull(info.getDiaryContext())){
                info.setDiaryContext(encrypDES.decrypt(info.getDiaryContext()));
            }
            if (!NullUtil.isNull(info.getDiaryHtml())){
                String html = encrypDES.decrypt(info.getDiaryHtml());
                info.setDiaryHtml(html);
            }
        });
        return list;
    }
    @Override
    public ResultBody<List<DiaryDTO>> findAllDiary(DailySearch search) {
        ResultBody resultBody = new ResultBody();
        resultBody.setData(findDiaryList(search));
        resultBody.setCount(diaryDao.count4FindAllDiary(search));
        return resultBody;
    }

    /**
     * 保存日记.
     * @param info 日记
     * @return 返回结果
     */
    @Override
    public ResultBody saveDiary(DiaryDTO info) {
        int ok =0;
        if (!NullUtil.isNull(info.getDiaryContext())){
            info.setDiaryContext(encrypDES.encrypt(info.getDiaryContext()));
        }
        if (!NullUtil.isNull(info.getDiaryHtml())){
            info.setDiaryHtml(encrypDES.encrypt(info.getDiaryHtml()));
        }
        if (info.getDiaryId()==null){
            info.setCreated(new Date());
            info.setUpdated(new Date());
            ok = diaryMapper.insert(info);
            if (!NullUtil.isNull(info)&&!NullUtil.isNull(info.getMood())){
                labelService.batchSave(info.moodLabels());
            }
        }else{
            DiaryExample params = new DiaryExample();
            params.createCriteria().andDiaryIdEqualTo(info.getDiaryId());
            info.setUpdated(new Date());
            ok = diaryMapper.updateByExampleSelective(info,params);
            labelService.delete(info.getDiaryId(),1,info.getUserId());
            if (!NullUtil.isNull(info) && !NullUtil.isNull(info.getMood())) {
                labelService.batchSave(info.moodLabels());
            }
        }
        return Tools.getSaveResultBody(ok);
    }

    /**
     * 日记详情.
     * search里面传日记id
     * @param search
     * @return
     */
    @Override
    public ResultBody detail(DailySearch search) {
        List list=findDiaryList(search);
        if (NullUtil.listIsNull(list))
            return new ResultBody();
        return new ResultBody(ResultCodeMsg.OK.getCode(),ResultCodeMsg.OK.getMsg(),list.get(0));
    }
}
