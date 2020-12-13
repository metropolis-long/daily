package com.daily.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cost implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.cost_id
     *
     * @mbggenerated
     */
    private Long costId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.cost_context
     *
     * @mbggenerated
     */
    private String costContext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.cost_money
     *
     * @mbggenerated
     */
    private BigDecimal costMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.tag
     *
     * @mbggenerated
     */
    private String tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.created
     *
     * @mbggenerated
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cost.updated
     *
     * @mbggenerated
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cost
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.cost_id
     *
     * @return the value of cost.cost_id
     *
     * @mbggenerated
     */
    public Long getCostId() {
        return costId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.cost_id
     *
     * @param costId the value for cost.cost_id
     *
     * @mbggenerated
     */
    public void setCostId(Long costId) {
        this.costId = costId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.user_id
     *
     * @return the value of cost.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.user_id
     *
     * @param userId the value for cost.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.cost_context
     *
     * @return the value of cost.cost_context
     *
     * @mbggenerated
     */
    public String getCostContext() {
        return costContext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.cost_context
     *
     * @param costContext the value for cost.cost_context
     *
     * @mbggenerated
     */
    public void setCostContext(String costContext) {
        this.costContext = costContext == null ? null : costContext.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.cost_money
     *
     * @return the value of cost.cost_money
     *
     * @mbggenerated
     */
    public BigDecimal getCostMoney() {
        return costMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.cost_money
     *
     * @param costMoney the value for cost.cost_money
     *
     * @mbggenerated
     */
    public void setCostMoney(BigDecimal costMoney) {
        this.costMoney = costMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.tag
     *
     * @return the value of cost.tag
     *
     * @mbggenerated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.tag
     *
     * @param tag the value for cost.tag
     *
     * @mbggenerated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.created
     *
     * @return the value of cost.created
     *
     * @mbggenerated
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.created
     *
     * @param created the value for cost.created
     *
     * @mbggenerated
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cost.updated
     *
     * @return the value of cost.updated
     *
     * @mbggenerated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cost.updated
     *
     * @param updated the value for cost.updated
     *
     * @mbggenerated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}