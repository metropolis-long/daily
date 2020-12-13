package com.daily.dto;

/**
 *
 */
public enum ResultCodeMsg {
    OK(200,"成功",1),
    ERROR(-1,"失败",1),
    SAVE_ERR(-2,"保存失败",1),
    REG_ERR(300,"注册失败",1),
    NO_USER(501,"用户不存在",1),
    LOGIN_PWD_ERR(301,"密码错误",1),
    PARAM_IS_BLANK(400,"参数为空",1),
    NO_LOGIN(100,"未登录",1)
    ;
    private int code=200;
    private String msg;
    /**
     *消息类型，默认1 web端
     */
    private int type;

    ResultCodeMsg() {
    }

    ResultCodeMsg(int code, String msg, int type) {
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static void main(String[] args) {
        System.out.println(ResultCodeMsg.OK.getMsg());
    }
}
