package com.daily.dto;

import java.io.Serializable;

/**
 * @ClassName WebSite
 * @Description TODO
 * @Author metropolis-long
 * @Date 2020/11/12 23:09
 * @VERSION 1.0.0
 */
public class WebSite implements Serializable {
    private Long relationId;
    private Integer relationTable;
    private String keywords;

    public WebSite() {
    }

    public WebSite(Long relationId, Integer relationTable, String keywords) {
        this.relationId = relationId;
        this.relationTable = relationTable;
        this.keywords = keywords;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationTable() {
        return relationTable;
    }

    public void setRelationTable(Integer relationTable) {
        this.relationTable = relationTable;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
