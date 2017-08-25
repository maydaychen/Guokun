package com.example.user.guokun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2017/8/25.
 */

public class CouponBean {
    /**
     * code : 1
     * data : [{"addtime":"2017-08-23 20:28:18","endtime":"2017-08-26 19:28:34","id":1,"money":3,"name":"新用户优惠券","starttime":"2017-08-22 20:28:35","status":0,"type":1,"updatetime":"2017-08-23 20:58:06"}]
     * mag : 获取优惠券成功
     */

    private int code;
    private String mag;
    private List<DataBean> data;

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

    public static class DataBean implements Serializable{
        /**
         * addtime : 2017-08-23 20:28:18
         * endtime : 2017-08-26 19:28:34
         * id : 1
         * money : 3.0
         * name : 新用户优惠券
         * starttime : 2017-08-22 20:28:35
         * status : 0
         * type : 1
         * updatetime : 2017-08-23 20:58:06
         */

        private String addtime;
        private String endtime;
        private int id;
        private double money;
        private String name;
        private String starttime;
        private int status;
        private int type;
        private String updatetime;

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}
