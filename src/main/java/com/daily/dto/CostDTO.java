package com.daily.dto;

import com.daily.pojo.Cost;
import com.daily.tool.NullUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CostDTO extends Cost {

    private String  timeStr;
    private String  sumMoney;

    private List<LabelDTO> labels;
    private List<LabelDTO> label;
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
                label.setRelationId(super.getCostId());
                label.setRelationTable(2);
                label.setLabelType(2);
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

    public String getTimeStr() {
        return timeStr==null?"合计":timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }
}
