package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by 译 on 2017/8/15.
 */

public class ChargeBean {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    /**
     * code : 1
     * data : [{"addTime":"2017-08-09 10:55:24","deposit":10,"enabled":1," id":2,"money":10,"type":1,"updateTime":"2017-08-09 13:41:04"}]
     * mag : 获取充值列表成功
     */

    private int code;
    private String mag;
    private java.util.List<DataBean> data;

    public class DataBean {
        /**
         * addTime : 2017-08-09 10:55:24
         * deposit : 10
         * enabled : 1
         *  id : 2
         * money : 10
         * type : 1
         * updateTime : 2017-08-09 13:41:04
         */

        private String addTime;
        private double deposit;
        private int enabled;
        private int id;
        private double money;
        private int type;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }



        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }
    }
}
