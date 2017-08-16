package com.example.user.guokun.bean;

/**
 * Created by user on 2017/8/16.
 */

public class CheckInfoBean {
    /**
     * code : 1
     * data : {"id":"201708101445131405","is_used":0,"out_trade_no":"20170 810144513362630","pay_money":0.01,"status":0}
     * mag : 获取订单数据成功
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
         * id : 201708101445131405
         * is_used : 0
         * out_trade_no : 20170 810144513362630
         * pay_money : 0.01
         * status : 0
         */

        private String id;
        private int is_used;
        private String out_trade_no;
        private double pay_money;
        private int status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIs_used() {
            return is_used;
        }

        public void setIs_used(int is_used) {
            this.is_used = is_used;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public double getPay_money() {
            return pay_money;
        }

        public void setPay_money(double pay_money) {
            this.pay_money = pay_money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
