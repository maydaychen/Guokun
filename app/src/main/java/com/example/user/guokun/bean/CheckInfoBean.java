package com.example.user.guokun.bean;

/**
 * Created by user on 2017/8/16.
 */

public class CheckInfoBean {
    /**
     * status : 1
     * data : {"add_time":"2017-07-24 09:54:44.0","id":1034,"is_valid":1,"name":"微盛测试","prices":0.01,"time_len":6,"type_id":7,"update_time":"2017-07-28 11:21:04.0"}
     * message : 设备启动成功
     */

    private int status;
    private DataBean data;
    private String message;

    public int getCode() {
        return status;
    }

    public void setCode(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMag() {
        return message;
    }

    public void setMag(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * add_time : 2017-07-24 09:54:44.0
         * id : 1034
         * is_valid : 1
         * name : 微盛测试
         * prices : 0.01
         * time_len : 6
         * type_id : 7
         * update_time : 2017-07-28 11:21:04.0
         */

        private String add_time;
        private int id;
        private int is_valid;
        private String name;
        private double prices;
        private int time_len;
        private int type_id;
        private String update_time;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(int is_valid) {
            this.is_valid = is_valid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrices() {
            return prices;
        }

        public void setPrices(double prices) {
            this.prices = prices;
        }

        public int getTime_len() {
            return time_len;
        }

        public void setTime_len(int time_len) {
            this.time_len = time_len;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
