package com.example.user.guokun.bean;

/**
 * Created by user on 2017/8/15.
 */

public class ResultBean {
    /**
     * code : 1
     * mag : 获取验证码成功
     */

    private int status;
    private String message;

    public int getCode() {
        return status;
    }

    public void setCode(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
