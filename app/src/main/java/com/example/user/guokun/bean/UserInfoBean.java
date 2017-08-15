package com.example.user.guokun.bean;

/**
 * Created by user on 2017/8/15.
 */

public class UserInfoBean {
    /**
     * code : 1
     * data : {"head_imgurl":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEI67H 1hTjBW8wGv4gBZf5ibVwGtRjP6D7HDTiaMNM2A7VDniag4ZMSy7dv9wFYQ2y5 FHhHQaPmicSA12g/0","id":1000980,"insert_dt":"2017-07-28 11:15:06.0","nick_name":"起风了","user_deposit":169.91,"user_score":0,"wx_sex":1}
     * mag : 获取用户 信息成功
     */

    private int code;
    private DataBean data;
    private String mag;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public static class DataBean {
        /**
         * head_imgurl : http://wx.qlogo.cn/mmopen/PiajxSqBRaEI67H 1hTjBW8wGv4gBZf5ibVwGtRjP6D7HDTiaMNM2A7VDniag4ZMSy7dv9wFYQ2y5 FHhHQaPmicSA12g/0
         * id : 1000980
         * insert_dt : 2017-07-28 11:15:06.0
         * nick_name : 起风了
         * user_deposit : 169.91
         * user_score : 0
         * wx_sex : 1
         */

        private String head_imgurl;
        private int id;
        private String insert_dt;
        private String nick_name;
        private double user_deposit;
        private int user_score;
        private int wx_sex;

        public String getHead_imgurl() {
            return head_imgurl;
        }

        public void setHead_imgurl(String head_imgurl) {
            this.head_imgurl = head_imgurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInsert_dt() {
            return insert_dt;
        }

        public void setInsert_dt(String insert_dt) {
            this.insert_dt = insert_dt;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public double getUser_deposit() {
            return user_deposit;
        }

        public void setUser_deposit(double user_deposit) {
            this.user_deposit = user_deposit;
        }

        public int getUser_score() {
            return user_score;
        }

        public void setUser_score(int user_score) {
            this.user_score = user_score;
        }

        public int getWx_sex() {
            return wx_sex;
        }

        public void setWx_sex(int wx_sex) {
            this.wx_sex = wx_sex;
        }
    }
}
