package com.daily.service;

import com.daily.dao.auto.LabelMapper;
import com.daily.dao.my.LabelDao;
import com.daily.dto.LabelDTO;
import com.daily.msg.ResultBody;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "label")
public class LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelDao labelDao;


    public ResultBody save(LabelDTO info) {

        int ok = 0;
        if (info.getLabelId()==null){
            info.setCreated(new Date());
            ok=labelMapper.insert(info);
        }else {
            ok=labelMapper.updateByPrimaryKeySelective(info);
        }
        return Tools.getSaveResultBody(ok);
    }

    public void batchSave(List<LabelDTO> list) {
        for (LabelDTO label:list){
            labelMapper.insert(label);
        }
    }

    public ResultBody delete(LabelDTO info) {
        int ok = labelDao.delete(info);
        return Tools.getSaveResultBody(ok);
    }
    public ResultBody deleteByPrimaryKey(Long labelId) {
        int ok = labelMapper.deleteByPrimaryKey(labelId);
        return Tools.getSaveResultBody(ok);
    }
    /**
     * 删除标签.
     * @param relationId 关联表id
     * @param relationTable 哪张表
     * @param userId 用户id
     * @return
     */
    public ResultBody delete(Long relationId,Integer relationTable,Long userId) {
        LabelDTO info = new LabelDTO();
        info.setRelationTable(relationTable);
        info.setRelationId(relationId);
        info.setUserId(userId);
        return delete(info);
    }
}
