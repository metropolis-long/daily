package com.daily.dto;

import com.daily.pojo.TravelNote;
import com.daily.pojo.TravelNoteWithBLOBs;
import com.daily.tool.NullUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TravelDTO
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/10/31 15:21
 * @VERSION 1.0.0
 */
public class TravelDTO extends TravelNoteWithBLOBs {

    private String provinceName;
    private String cityName;
    private String fileUrl;
    private String avatar;
    private String nickName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    //内部批量插入更新使用
    private List<LabelDTO> labels;
    //页面显示使用
    private List<LabelDTO> label;


    public List<LabelDTO> getLabel() {
        if (super.getTag()==null){
            return label;
        }
        List<LabelDTO> label= new ArrayList<>();
        String[] mood =super.getTag().split(",");
        for (String m:mood) {
            if (NullUtil.isNull(m))
                continue;
            String[] ms = m.split("，");
            for (String mm : ms){
                if (NullUtil.isNull(mm))
                    continue;
                LabelDTO s = new LabelDTO();
                s.setLabelContext(mm);
                label.add(s);
            }
        }
        return label;
    }

    public void setLabel(List<LabelDTO> label) {
        this.label = label;
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }
    public List<LabelDTO> moodLabels() {
        if (super.getTag()==null){
            return labels;
        }
        List<LabelDTO> labels= new ArrayList<>();
        String[] mood =super.getTag().split(",");
        for (String m:mood) {
            if (NullUtil.isNull(m))
                continue;
            String[] ms = m.split("，");
            for (String mm : ms){
                if (NullUtil.isNull(mm))
                    continue;
                LabelDTO label = new LabelDTO();
                label.setLabelContext(mm);
                label.setUserId(super.getUserId());
                label.setRelationId(super.getTravelId());
                label.setRelationTable(4);
                label.setLabelType(4);
                label.setCreated(new Date());
                labels.add(label);
            }
        }
        setLabels(labels);
        return labels;
    }

    public void setLabels(List<LabelDTO> labels) {
        this.labels = labels;
    }

}
