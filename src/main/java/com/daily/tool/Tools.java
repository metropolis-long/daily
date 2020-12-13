package com.daily.tool;

import com.daily.msg.ResultBody;
import com.daily.msg.ResultCodeMsg;

/**
 * 所有的普通工具方法.
 */
public class Tools {
    /**
     * 保存方法返回结果.
     * @param ok
     * @return
     */
    public static ResultBody getSaveResultBody(final int ok){
        return ok>0?new ResultBody(ResultCodeMsg.OK.getCode(),ResultCodeMsg.OK.getMsg()):new ResultBody(ResultCodeMsg.SAVE_ERR.getCode(),ResultCodeMsg.SAVE_ERR.getMsg());
    }
    /**
     * 删除方法返回结果.
     * @param ok
     * @return
     */
    public static ResultBody getDeleteResultBody(final int ok){
        return ok>0?new ResultBody(ResultCodeMsg.OK.getCode(),ResultCodeMsg.OK.getMsg()):new ResultBody(ResultCodeMsg.DELETE_ERR.getCode(),ResultCodeMsg.DELETE_ERR.getMsg());
    }
}
