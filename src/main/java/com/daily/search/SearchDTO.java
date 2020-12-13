package com.daily.search;

import com.daily.tool.NullUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 列表查询的查询对象.
 * @author metropolis-long.
 * 2016年12月11日.
 */
public class SearchDTO implements Serializable {
	private static final long serialVersionUID = 1317665968517579170L;

	private Integer statusId;
	/**
	 * 关键字.
	 */
	private String keywords;
	/**
	 * 分页信息.
	 */
	private  Page page;
	/**
	 * 排序方式.
	 */
	private String orderCause;
	private Long userId;
	public Long getUserId() {
		return userId;
	}

	public  Page getPage() {
		return page;
	}
	public void setPage( Page page) {
		this.page = page;
	}
	public String getOrderCause() {
		return orderCause;
	}
	public void setOrderCause(String orderCause) {
		this.orderCause = orderCause;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 用于 keywords SQL like的写法.
	 * @return
	 */
	@JsonIgnore
	public String getKeywordsLike() {
		if(NullUtil.isNull(keywords)){
			return "%%";
		}else{
			return "%"+ keywords.replace("'", "''")+"%";
		}
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
