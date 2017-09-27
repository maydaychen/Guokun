package com.example.user.guokun.bean;

import java.util.List;

/**
 * Created by 译 on 2017/8/15.
 */

public class ChargeBean {
    /**
     * status : 1
     * message : 请求成功
     * data : {"size":4,"list":[{"id":1,"add_time":"2017-09-13 17:00:54","update_time":"2017-09-13 17:00:57","type":1,"money":10,"deposit":12},{"id":2,"add_time":"2017-09-13 17:01:06","update_time":"2017-09-13 17:01:09","type":1,"money":20,"deposit":26},{"id":3,"add_time":"2017-09-13 17:01:20","update_time":"2017-09-13 17:01:22","type":1,"money":50,"deposit":75},{"id":4,"add_time":"2017-09-13 17:01:45","update_time":"2017-09-13 17:01:47","type":1,"money":100,"deposit":150}]}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * size : 4
         * list : [{"id":1,"add_time":"2017-09-13 17:00:54","update_time":"2017-09-13 17:00:57","type":1,"money":10,"deposit":12},{"id":2,"add_time":"2017-09-13 17:01:06","update_time":"2017-09-13 17:01:09","type":1,"money":20,"deposit":26},{"id":3,"add_time":"2017-09-13 17:01:20","update_time":"2017-09-13 17:01:22","type":1,"money":50,"deposit":75},{"id":4,"add_time":"2017-09-13 17:01:45","update_time":"2017-09-13 17:01:47","type":1,"money":100,"deposit":150}]
         */

        private int size;
        private List<ListBean> list;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * add_time : 2017-09-13 17:00:54
             * update_time : 2017-09-13 17:00:57
             * type : 1
             * money : 10
             * deposit : 12
             */

            private int id;
            private String add_time;
            private String update_time;
            private int type;
            private double money;
            private double deposit;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public double getDeposit() {
                return deposit;
            }

            public void setDeposit(double deposit) {
                this.deposit = deposit;
            }
        }
    }
}
