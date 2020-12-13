package com.daily.service;

import com.daily.dao.auto.DailyFilesMapper;
import com.daily.msg.ResultBody;
import com.daily.pojo.DailyFiles;
import com.daily.tool.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DailyFilesService
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/31 19:13
 * @VERSION 1.0.0
 */
@Service(value = "dailyFilesService")
public class DailyFilesService {
    @Autowired
    private DailyFilesMapper dailyFilesMapper;

    public ResultBody save(DailyFiles file){
        int ok = 0;
        if (file.getFileId()!=null){
            ok=dailyFilesMapper.updateByPrimaryKeySelective(file);
        }else {
            ok=dailyFilesMapper.insert(file);
        }
        return Tools.getSaveResultBody(ok);
    }
}
